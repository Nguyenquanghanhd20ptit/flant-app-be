package com.example.plantapp.service.plant;

import com.example.plantapp.commons.data.entity.PlantEntity;
import com.example.plantapp.commons.data.mapper.plant.PlantMapper;
import com.example.plantapp.commons.data.mapper.plant.PlantShortMapper;
import com.example.plantapp.commons.data.request.SearchRequest;
import com.example.plantapp.commons.data.response.PageResponse;
import com.example.plantapp.commons.data.response.plant.PlantShortResponse;
import com.example.plantapp.db.mysql.config.SpecificationConfig;
import com.example.plantapp.db.mysql.repository.IPlantRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantSearchService extends BaseService {
    @Autowired
    private IPlantRepository plantRepository;
    @Autowired
    private PlantShortMapper plantMapper;
    @Autowired
    private SpecificationConfig specificationConfig;
    public ResponseEntity<String> search(SearchRequest request){
        try {
            Specification<PlantEntity> spec = specificationConfig.buildSearch(request, PlantEntity.class);
            Pageable pageable = specificationConfig.buildPageable(request, PlantEntity.class);
            List<PlantEntity> plantEntities = plantRepository.findAll(spec,pageable).toList();
            Long total =  plantRepository.count(spec);
            List<PlantShortResponse> responses = plantMapper.toResponses(plantEntities);
            PageResponse<PlantShortResponse> pageResponse = new PageResponse<PlantShortResponse>()
                    .setItems(responses)
                    .setTotal(total);
            return createResponseSuccess(gsonDateFormat.toJson(pageResponse));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
