package com.example.plantapp.commons.data.response.scheduler;

import com.example.plantapp.commons.data.response.plant.PlantResponse;
import com.example.plantapp.commons.data.response.plant.PlantShortResponse;
import com.example.plantapp.commons.data.response.reminder.ReminderResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class SchedulerResponse {
    private Integer id;
    private PlantShortResponse plant;
    private List<ReminderResponse> reminders;
    private LocalDateTime createdAt;
}
