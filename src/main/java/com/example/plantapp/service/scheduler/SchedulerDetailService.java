package com.example.plantapp.service.scheduler;

import com.example.plantapp.commons.data.entity.SchedulerEntity;
import com.example.plantapp.commons.data.mapper.scheduler.SchedulerMapper;
import com.example.plantapp.commons.data.response.scheduler.SchedulerResponse;
import com.example.plantapp.db.mysql.repository.ISchedulerRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;

@Service
public class SchedulerDetailService extends BaseService {
    @Autowired
    private ISchedulerRepository schedulerRepository;
    @Autowired
    private SchedulerMapper schedulerMapper;
    public ResponseEntity<String> getById(Integer schedulerId){
        try {
            Optional<SchedulerEntity> optional = schedulerRepository.findById(schedulerId);
            if(!optional.isPresent()){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"Thông tin lịch trình không hợp lệ");
            }
            SchedulerEntity scheduler = optional.get();
            SchedulerResponse schedulerResponse = schedulerMapper.toResponse(scheduler);
            return createResponseSuccess(gsonDateFormat.toJson(schedulerResponse));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
