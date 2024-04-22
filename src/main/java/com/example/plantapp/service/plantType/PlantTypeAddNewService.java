package com.example.plantapp.service.plantType;

import com.example.plantapp.commons.data.entity.PlantTypeEntity;
import com.example.plantapp.commons.data.mapper.plantType.PlantTypeMapper;
import com.example.plantapp.commons.data.request.plantType.PlantTypeRequest;
import com.example.plantapp.db.mysql.repository.IPlantTypeRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class PlantTypeAddNewService extends BaseService {
    @Autowired
    private IPlantTypeRepository plantTypeRepository;
    @Autowired
    private PlantTypeMapper plantTypeMapper;

    public ResponseEntity<String> addPlantType(PlantTypeRequest request){
        try {
            if(!validateReq()){
                createResponseErrorValidate();
            }
            PlantTypeEntity plantType = plantTypeMapper.toEntity(request);
            PlantTypeEntity plantTypeReturn = plantTypeRepository.save(plantType);
            if(ObjectUtils.isEmpty(plantTypeReturn)){
                return createResponseErrorDuringProcess();
            }
            return createResponseSuccess( gson.toJson("Thêm mới thành công"));
        }catch (Exception e){
            return createResponseException(e);
        }
    }

    private boolean validateReq() {
        return true;
    }
}
