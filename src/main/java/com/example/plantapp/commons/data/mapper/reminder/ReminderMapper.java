package com.example.plantapp.commons.data.mapper.reminder;

import com.example.plantapp.commons.data.entity.ReminderEntity;
import com.example.plantapp.commons.data.entity.SchedulerEntity;
import com.example.plantapp.commons.data.request.reminder.ReminderRequest;
import com.example.plantapp.commons.data.response.reminder.ReminderResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.example.plantapp.commons.data.enums.PeriodEnum.*;
import static com.example.plantapp.utils.DateUtils.*;

@Mapper(componentModel = "spring")
public abstract class ReminderMapper {
    public abstract ReminderEntity toEntity(ReminderRequest request);
    @AfterMapping
    public void map(@MappingTarget ReminderEntity reminder, ReminderRequest request){
        reminder.setPeriod(genPeriod(request.getHour()));
        reminder.setScheduler(new SchedulerEntity().setId(request.getSchedulerId()));
    }

    private String genPeriod(Long hour) {
        LocalDateTime localDateTime = longToLocalDateTime(hour);
        Long hourOfDay = localTimeToLong( LocalTime.of(localDateTime.getHour(),localDateTime.getMinute(),localDateTime.getSecond()));
        if(hourOfDay < localTimeToLong( LocalTime.of(12,0,0))){
            return MORNING.getPeriod();
        }
        else if(hourOfDay < localTimeToLong(LocalTime.of(18,0,0))){
            return AFTERNOON.getPeriod();
        }
        else{
            return EVENING.getPeriod();
        }
    }

    public abstract ReminderResponse toResponse(ReminderEntity reminder);
    public abstract List<ReminderResponse> toResponses(List<ReminderEntity> reminders);
}
