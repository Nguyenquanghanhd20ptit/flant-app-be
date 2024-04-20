package com.example.plantapp.service.scheduler;

import com.example.plantapp.commons.data.entity.SchedulerEntity;
import com.example.plantapp.commons.data.entity.UserEntity;
import com.example.plantapp.commons.data.mapper.scheduler.SchedulerMapper;
import com.example.plantapp.commons.data.response.scheduler.SchedulerResponse;
import com.example.plantapp.db.mysql.repository.ISchedulerRepository;
import com.example.plantapp.db.mysql.repository.IUserRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;

@Service
public class SchedulerAllService extends BaseService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private SchedulerMapper schedulerMapper;


    public ResponseEntity<String> getAllScheduler(Integer userId){
        try {
            UserEntity userEntity = userRepository.getById(userId);
            if(ObjectUtils.isEmpty(userEntity)){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"User không tồn tại");
            }
            List<SchedulerEntity> schedulerEntities = userEntity.getSchedulers();
            List<SchedulerResponse> schedulerResponses = schedulerMapper.toResponses(schedulerEntities);
            String data = gsonDateFormat.toJson(schedulerResponses);
            return createResponseSuccess(data);
        }catch (Exception e){
            return  createResponseException(e);
        }
    }
}
