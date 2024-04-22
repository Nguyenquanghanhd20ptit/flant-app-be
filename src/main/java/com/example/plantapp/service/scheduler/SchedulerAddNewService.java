package com.example.plantapp.service.scheduler;

import com.example.plantapp.commons.data.entity.PlantEntity;
import com.example.plantapp.commons.data.entity.SchedulerEntity;
import com.example.plantapp.commons.data.entity.UserEntity;
import com.example.plantapp.commons.data.request.scheduler.SchedulerRequest;
import com.example.plantapp.db.mysql.repository.IPlantRepository;
import com.example.plantapp.db.mysql.repository.ISchedulerRepository;
import com.example.plantapp.db.mysql.repository.IUserRepository;
import com.example.plantapp.service.BaseService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;

@Service
public class SchedulerAddNewService extends BaseService {
    @Autowired
    private IPlantRepository plantRepository;
    @Autowired
    private ISchedulerRepository schedulerRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private Gson gson = new Gson();

    public ResponseEntity<String> addNew(SchedulerRequest request){
        try{
            if(!validateReq(request)){
                createResponseErrorValidate();
            }
            if(request.getPlantId() == null || request.getPlantId() < 1){
                return createResponseErrorValidate();
            }
            Optional<PlantEntity> opt = plantRepository.findById(request.getPlantId());
            if(!opt.isPresent()){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"Plant không tồn tại");
            }
            Optional<UserEntity> optUser = userRepository.findById(request.getCreatedBy());
            if(!optUser.isPresent()){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"User không tồn tại");
            }
            Optional<SchedulerEntity> optScheduler = schedulerRepository.findByPlantAndUser(request.getPlantId(),
                    request.getCreatedBy());
            if(optScheduler.isPresent()){
                return createResponseSuccess(gson.toJson("Đã có sẵn lịch trình"));
            }
            SchedulerEntity scheduler = new SchedulerEntity()
                    .setPlant(new PlantEntity().setId(request.getPlantId()))
                    .setCreatedBy(new UserEntity().setId(request.getCreatedBy()));
            SchedulerEntity schedulerReturn = schedulerRepository.save(scheduler);
            if(ObjectUtils.isEmpty(schedulerReturn)){
                return createResponseErrorDuringProcess();
            }
            return createResponseSuccess(gson.toJson("Thêm lịch trình thành công"));
        }catch (Exception e){
            return createResponseException(e);
        }
    }

    protected boolean validateReq(SchedulerRequest request) {
        if(request.getPlantId() == null || request.getPlantId() < 1){
            invalidMessage = "plant không hợp lệ";
            return false;
        }
        return true;
    }
}
