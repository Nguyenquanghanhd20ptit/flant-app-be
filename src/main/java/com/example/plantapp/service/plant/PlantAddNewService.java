package com.example.plantapp.service.plant;

import com.example.plantapp.commons.data.entity.PlantEntity;
import com.example.plantapp.commons.data.entity.PlantTypeEntity;
import com.example.plantapp.commons.data.mapper.plant.PlantMapper;
import com.example.plantapp.commons.data.request.plant.PlantRequest;
import com.example.plantapp.db.mysql.repository.IPlantRepository;
import com.example.plantapp.db.mysql.repository.IPlantTypeRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;

@Service
public class PlantAddNewService extends BaseService {
    @Autowired
    private IPlantRepository plantRepository;
    @Autowired
    private IPlantTypeRepository plantTypeRepository;
    @Autowired
    private PlantMapper plantMapper;
    public ResponseEntity<String> addPlant(PlantRequest plantRequest){

       try {
           if(!validateReq()){
               createResponseErrorValidate();
           }
           Optional<PlantTypeEntity> opt = plantTypeRepository.findById(plantRequest.getPlantTypeId());
           if(!opt.isPresent()){
               return createResponseError(ERROR_CODE_NOT_INFORMATION,"PlantTypeId không tồn tại");
           }
           PlantEntity plantEntity = plantMapper.toEntity(plantRequest);
           plantEntity.setPlantType(new PlantTypeEntity().setId(plantRequest.getPlantTypeId()));
           PlantEntity plantReturn = plantRepository.save(plantEntity);
           if(ObjectUtils.isEmpty(plantReturn)){
               return createResponseErrorDuringProcess();
           }
           return createResponseSuccess("Thêm mới cây thành công");
        }catch (Exception e){
           return createResponseException(e);
       }
    }

    private boolean validateReq() {
        return true;
    }
}
