package com.example.plantapp.service.reminder;

import com.example.plantapp.commons.data.entity.ReminderEntity;
import com.example.plantapp.db.mysql.repository.IReminderRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;

@Service
public class ReminderDeleteService extends BaseService {
    @Autowired
    private IReminderRepository reminderRepository;
    public ResponseEntity<String> deleteById(Integer reminderId){
        try {
            Optional<ReminderEntity> opt = reminderRepository.findById(reminderId);
            if(!opt.isPresent()){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"Thông tin lời nhắc không hợp lệ");
            }
            reminderRepository.deleteById(reminderId);
            return createResponseSuccess(gson.toJson("Xóa lời nhắc thành công"));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
