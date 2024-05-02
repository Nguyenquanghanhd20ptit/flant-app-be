package com.example.plantapp.commons.data.response.notification;

import com.example.plantapp.commons.data.response.plant.PlantShortResponse;
import com.example.plantapp.commons.data.response.reminder.ReminderResponse;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class NotificationResponse {
    private PlantShortResponse plant;
    private ReminderResponse reminder;
}
