package com.example.plantapp.commons.data.response.reminder;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ReminderResponse {
    private Integer id;
    private String work;
    private Integer frequency;
    private LocalDateTime specificDate;
    private LocalDateTime hour;
    private LocalDateTime timeStart;
    private String period;
    private String note;
    private LocalDateTime createdAt;
}
