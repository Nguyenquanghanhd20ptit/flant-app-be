package com.example.plantapp.service.scheduler;

import com.example.plantapp.commons.data.entity.PlantEntity;
import com.example.plantapp.commons.data.entity.SchedulerEntity;
import com.example.plantapp.commons.data.entity.UserEntity;
import com.example.plantapp.commons.data.mapper.scheduler.SchedulerMapper;
import com.example.plantapp.commons.data.request.scheduler.SchedulerRequest;
import com.example.plantapp.commons.data.response.scheduler.SchedulerResponse;
import com.example.plantapp.db.mysql.repository.IPlantRepository;
import com.example.plantapp.db.mysql.repository.ISchedulerRepository;
import com.example.plantapp.db.mysql.repository.IUserRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;

@Service
public class SchedulerPlantUserService extends BaseService {

    @Autowired
    private ISchedulerRepository schedulerRepository;
    @Autowired
    private SchedulerMapper schedulerMapper;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPlantRepository plantRepository;
    public ResponseEntity<String> findByPlantAndUser(Integer plantId, Integer userId){
        try {
            Optional<UserEntity> optUser = userRepository.findById(userId);
            if(!optUser.isPresent()){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"User không tồn tại");
            }
            if(plantId == null){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"Hãy chọn cây bạn muốn thêm");
            }
            Optional<PlantEntity> opt = plantRepository.findById(plantId);
            if(!opt.isPresent()){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"Plant không tồn tại");
            }
            Optional<SchedulerEntity> optional = schedulerRepository.findByPlantAndUser(plantId,userId);
            if(!optional.isPresent()){
                SchedulerEntity request = new SchedulerEntity()
                        .setPlant(new PlantEntity().setId(plantId))
                        .setCreatedBy(new UserEntity().setId(userId));
                SchedulerEntity scheduler = schedulerRepository.save(request);
                SchedulerResponse schedulerResponse = schedulerMapper.toResponse(scheduler);
                return createResponseSuccess(gsonDateFormat.toJson(schedulerResponse));
            }
            SchedulerEntity scheduler = optional.get();
            SchedulerResponse schedulerResponse = schedulerMapper.toResponse(scheduler);
            return createResponseSuccess(gsonDateFormat.toJson(schedulerResponse));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
