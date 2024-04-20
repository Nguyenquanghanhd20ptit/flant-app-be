package com.example.plantapp.commons.data.mapper.plantType;

import com.example.plantapp.commons.data.entity.PlantEntity;
import com.example.plantapp.commons.data.entity.PlantTypeEntity;
import com.example.plantapp.commons.data.mapper.AbsMapper;
import com.example.plantapp.commons.data.request.plantType.PlantTypeRequest;
import com.example.plantapp.commons.data.response.plantType.PlantTypeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PlantTypeMapper extends AbsMapper<PlantTypeRequest, PlantTypeResponse, PlantTypeEntity> {

}
