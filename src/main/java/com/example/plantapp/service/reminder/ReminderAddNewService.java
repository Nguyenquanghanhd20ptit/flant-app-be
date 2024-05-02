package com.example.plantapp.service.reminder;

import com.example.plantapp.commons.data.entity.ReminderEntity;
import com.example.plantapp.commons.data.entity.ReminderTimeEntity;
import com.example.plantapp.commons.data.entity.SchedulerEntity;
import com.example.plantapp.commons.data.mapper.reminder.ReminderMapper;
import com.example.plantapp.commons.data.request.reminder.ReminderRequest;
import com.example.plantapp.db.mysql.repository.IReminderRepository;
import com.example.plantapp.db.mysql.repository.IReminderTimeRepository;
import com.example.plantapp.db.mysql.repository.ISchedulerRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;
import static com.example.plantapp.utils.DateUtils.*;

@Service
public class ReminderAddNewService extends BaseService {
    @Autowired
    private ISchedulerRepository schedulerRepository;
    @Autowired
    private IReminderRepository reminderRepository;
    @Autowired
    private ReminderMapper reminderMapper;

    @Autowired
    private IReminderTimeRepository reminderTimeRepository;

    @Transactional
    public ResponseEntity<String> addNew(ReminderRequest request) {
        if (!validateReq(request)) {
            return createResponseErrorValidate();
        }
        Optional<SchedulerEntity> opt = schedulerRepository.findById(request.getSchedulerId());
        if (!opt.isPresent()) {
            return createResponseError(ERROR_CODE_NOT_INFORMATION, "Thông tin lịch trình không hợp lệ");
        }
        ReminderEntity reminder = reminderMapper.toEntity(request);
        ReminderEntity reminderReturn = reminderRepository.save(reminder);

        //save reminder time
        if (reminderReturn.getSpecificDate() != null) {
            ReminderTimeEntity reminderTimeEntity = new ReminderTimeEntity()
                    .setSpecificDate(reminderReturn.getSpecificDate())
                    .setHour(reminderReturn.getHour())
                    .setReminder(reminderReturn)
                    .setPlant(opt.get().getPlant());
            reminderTimeRepository.save(reminderTimeEntity);
        } else if (reminderReturn.getFrequency() != null) {
            List<ReminderTimeEntity> reminderTimeEntities = new ArrayList<>();
            LocalDateTime timeStart = longToLocalDateTime(reminderReturn.getTimeStart());
            Integer numFrequency = (7 / reminderReturn.getFrequency());
            for (int i = 0; i < numFrequency; i++) {
                Long time = localDateTimeToLong(timeStart.plusDays(reminderReturn.getFrequency() * i));
                ReminderTimeEntity reminderTimeEntity = new ReminderTimeEntity()
                        .setSpecificDate(time)
                        .setHour(reminderReturn.getHour())
                        .setReminder(reminderReturn)
                        .setPlant(opt.get().getPlant());
                reminderTimeEntities.add(reminderTimeEntity);
            }
            reminderTimeRepository.saveAll(reminderTimeEntities);
        }
        if (ObjectUtils.isEmpty(reminderReturn)) {
            return createResponseErrorDuringProcess();
        }
        return createResponseSuccess(gson.toJson("Thêm lời nhắc thành công"));
    }

    private boolean validateReq(ReminderRequest request) {
        if (request.getFrequency() == null
                && (request.getSpecificDate() == null)) {
            invalidMessage = "Vui lòng chọn ngày thực hiện lời nhắc";
            return false;
        }
        if (request.getFrequency() != null && request.getFrequency() < 1) {
            invalidMessage = "Lời nhắc thực hiện hàng ngày không hợp lệ";
            return false;
        }
        if (request.getSpecificDate() != null && request.getSpecificDate() < localDateTimeToLong(LocalDateTime.now())) {
            invalidMessage = "Lời nhắc thực hiện ngày cụ thể không hợp lệ";
            return false;
        }
        if (request.getTimeStart() < localDateTimeToLong(localDateTimeNow().minusDays(1))) {
            invalidMessage = "Thời gian bắt đầu không hợp lệ";
            return false;
        }
        if (request.getSpecificDate() != null) {
            if (request.getTimeStart() > request.getSpecificDate()) {
                invalidMessage = "Thời gian bắt đầu không được lớn hơn ngày bạn đã chọn";
                return false;
            }
        }
        return true;
    }
}
