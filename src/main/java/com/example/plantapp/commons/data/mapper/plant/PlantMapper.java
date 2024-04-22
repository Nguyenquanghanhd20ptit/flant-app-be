package com.example.plantapp.commons.data.mapper.plant;

import com.example.plantapp.commons.data.entity.PlantEntity;
import com.example.plantapp.commons.data.entity.PlantTypeEntity;
import com.example.plantapp.commons.data.mapper.AbsMapper;
import com.example.plantapp.commons.data.request.plant.PlantRequest;
import com.example.plantapp.commons.data.request.plantType.PlantTypeRequest;
import com.example.plantapp.commons.data.response.plant.PlantResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class PlantMapper extends AbsMapper<PlantRequest, PlantResponse, PlantEntity> {
    @Override
    public abstract PlantEntity toEntity(PlantRequest req);
    @AfterMapping
    public void map(@MappingTarget PlantEntity entity, PlantRequest request){
        entity.setPlantType(new PlantTypeEntity().setId(request.getPlantTypeId()));
    }
}
