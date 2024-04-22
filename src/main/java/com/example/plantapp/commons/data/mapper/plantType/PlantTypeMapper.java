package com.example.plantapp.commons.data.mapper.plantType;

import com.example.plantapp.commons.data.entity.PlantEntity;
import com.example.plantapp.commons.data.entity.PlantTypeEntity;
import com.example.plantapp.commons.data.mapper.AbsMapper;
import com.example.plantapp.commons.data.request.plant.PlantRequest;
import com.example.plantapp.commons.data.request.plantType.PlantTypeRequest;
import com.example.plantapp.commons.data.response.plantType.PlantTypeResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PlantTypeMapper extends AbsMapper<PlantTypeRequest, PlantTypeResponse, PlantTypeEntity> {
}
