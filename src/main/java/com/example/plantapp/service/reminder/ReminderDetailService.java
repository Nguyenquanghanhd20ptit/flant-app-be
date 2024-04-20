package com.example.plantapp.service.reminder;

import com.example.plantapp.commons.data.entity.ReminderEntity;
import com.example.plantapp.commons.data.entity.SchedulerEntity;
import com.example.plantapp.commons.data.mapper.reminder.ReminderMapper;
import com.example.plantapp.commons.data.response.reminder.ReminderResponse;
import com.example.plantapp.commons.data.response.scheduler.SchedulerResponse;
import com.example.plantapp.db.mysql.repository.IReminderRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;

@Service
public class ReminderDetailService extends BaseService {
    @Autowired
    private IReminderRepository reminderRepository;
    @Autowired
    private ReminderMapper reminderMapper;
    public ResponseEntity<String> getById(Integer reminderId){
        try {
            Optional<ReminderEntity> optional = reminderRepository.findById(reminderId);
            if(!optional.isPresent()){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"Thông tin lời nhắc không hợp lệ");
            }
            ReminderEntity reminder = optional.get();
            ReminderResponse response = reminderMapper.toResponse(reminder);
            return createResponseSuccess(gsonDateFormat.toJson(response));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
