package com.example.plantapp.service.reminder;

import com.example.plantapp.commons.data.entity.ReminderEntity;
import com.example.plantapp.commons.data.entity.SchedulerEntity;
import com.example.plantapp.commons.data.mapper.reminder.ReminderMapper;
import com.example.plantapp.commons.data.request.reminder.ReminderRequest;
import com.example.plantapp.db.mysql.repository.IReminderRepository;
import com.example.plantapp.db.mysql.repository.ISchedulerRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;
import static com.example.plantapp.utils.DateUtils.localDateTimeToLong;
import static com.example.plantapp.utils.DateUtils.localTimeToLong;

@Service
public class ReminderAddNewService extends BaseService {
    @Autowired
    private ISchedulerRepository schedulerRepository;
    @Autowired
    private IReminderRepository reminderRepository;
    @Autowired
    private ReminderMapper reminderMapper;

    public ResponseEntity<String> addNew(ReminderRequest request){
        if(!validateReq(request)){
            return createResponseErrorValidate();
        }
        Optional<SchedulerEntity> opt = schedulerRepository.findById(request.getSchedulerId());
        if(!opt.isPresent()){
            return createResponseError(ERROR_CODE_NOT_INFORMATION,"Thông tin lịch trình không hợp lệ");
        }
        ReminderEntity reminder = reminderMapper.toEntity(request);
        ReminderEntity reminderReturn = reminderRepository.save(reminder);
        if(ObjectUtils.isEmpty(reminderReturn)){
            return createResponseErrorDuringProcess();
        }
        return createResponseSuccess(gson.toJson("Thêm lời nhắc thành công"));
    }

    private boolean validateReq(ReminderRequest request) {
        if(request.getFrequency() == null
                && (request.getSpecificDate() == null)){
            invalidMessage = "Vui lòng chọn ngày thực hiện lời nhắc";
            return false;
        }
        if(request.getFrequency() != null && request.getFrequency() < 1  ){
            invalidMessage = "Lời nhắc thực hiện hàng ngày không hợp lệ";
            return false;
        }
        if(request.getSpecificDate() != null && request.getSpecificDate() < localDateTimeToLong(LocalDateTime.now()) ){
            invalidMessage = "Lời nhắc thực hiện ngày cụ thể không hợp lệ";
            return false;
        }
        if(request.getTimeStart() < localDateTimeToLong(LocalDateTime.now())){
            invalidMessage = "Thời gian bắt đầu không hợp lệ";
            return false;
        }
        if (request.getSpecificDate() != null ){
            if(request.getTimeStart() > request.getSpecificDate() ){
                invalidMessage = "Thời gian bắt đầu không được lớn hơn ngày bạn đã chọn";
                return false;
            }
        }
        return true;
    }
}
