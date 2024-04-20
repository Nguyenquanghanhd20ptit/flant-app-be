package com.example.plantapp.commons.data.mapper.scheduler;

import com.example.plantapp.commons.data.entity.SchedulerEntity;
import com.example.plantapp.commons.data.mapper.plant.PlantMapper;
import com.example.plantapp.commons.data.mapper.plant.PlantShortMapper;
import com.example.plantapp.commons.data.mapper.reminder.ReminderMapper;
import com.example.plantapp.commons.data.response.scheduler.SchedulerResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public  abstract class SchedulerMapper {

    @Autowired
    private ReminderMapper reminderMapper;
    @Autowired
    private PlantShortMapper plantMapper;
    @Mapping(target = "plant", ignore = true)
    @Mapping(target = "reminders", ignore = true)
    public abstract SchedulerResponse toResponse(SchedulerEntity entity);

    @AfterMapping
    public void map(@MappingTarget SchedulerResponse response, SchedulerEntity entity){
        response.setPlant(plantMapper.toResponse(entity.getPlant()));
        response.setReminders(reminderMapper.toResponses(entity.getReminders()));
    }
    public abstract List<SchedulerResponse> toResponses(List<SchedulerEntity> schedulerEntities);
}
