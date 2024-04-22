package com.example.plantapp.service.scheduler;

import com.example.plantapp.commons.data.entity.SchedulerEntity;
import com.example.plantapp.db.mysql.repository.IReminderRepository;
import com.example.plantapp.db.mysql.repository.ISchedulerRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;

@Service
public class SchedulerDeleteService  extends BaseService {
    @Autowired
    private ISchedulerRepository schedulerRepository;
    @Autowired
    private IReminderRepository reminderRepository;

    @Transactional
    public ResponseEntity<String> deleteById(Integer schedulerId){
        try {
            Optional<SchedulerEntity> optional = schedulerRepository.findById(schedulerId);
            if(!optional.isPresent()){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"Thông tin lịch trình không hợp lệ");
            }
            SchedulerEntity scheduler = optional.get();
            reminderRepository.deleteBySchedulerId(scheduler.getId());
            schedulerRepository.deleteById(scheduler.getId());
            return createResponseSuccess(gson.toJson("Xóa lịch trình thành công"));
        }catch (Exception e){
            e.printStackTrace();
            return createResponseException(e);
        }

    }
}
