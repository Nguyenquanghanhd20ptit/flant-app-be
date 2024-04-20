package com.example.plantapp.commons.data.mapper.plant;

import com.example.plantapp.commons.data.entity.PlantEntity;
import com.example.plantapp.commons.data.mapper.AbsMapper;
import com.example.plantapp.commons.data.request.plant.PlantRequest;
import com.example.plantapp.commons.data.response.plant.PlantShortResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PlantShortMapper extends AbsMapper<PlantRequest, PlantShortResponse, PlantEntity> {

}
