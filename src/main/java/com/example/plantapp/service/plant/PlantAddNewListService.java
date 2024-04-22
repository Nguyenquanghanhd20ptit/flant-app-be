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

import java.util.List;
import java.util.Optional;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;
@Service
public class PlantAddNewListService extends BaseService {
    @Autowired
    private IPlantRepository plantRepository;
    @Autowired
    private IPlantTypeRepository plantTypeRepository;
    @Autowired
    private PlantMapper plantMapper;
    public ResponseEntity<String> addPlants(List<PlantRequest> plantRequest){

        try {
            if(!validateReq()){
                createResponseErrorValidate();
            }
            List<PlantEntity> plantEntities = plantMapper.toEntities(plantRequest);
            List<PlantEntity> plantReturns = plantRepository.saveAll(plantEntities);
            if(ObjectUtils.isEmpty(plantReturns)){
                return createResponseErrorDuringProcess();
            }
            return createResponseSuccess(gson.toJson("Thêm  mới cây thành công"));
        }catch (Exception e){
            return createResponseException(e);
        }
    }

    private boolean validateReq() {
        return true;
    }
}
