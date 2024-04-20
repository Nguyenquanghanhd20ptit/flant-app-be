package com.example.plantapp.commons.data.request.scheduler.request.reminder;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class ReminderRequest {
    @NotBlank(message = "Vui lòng chọn công việc")
    private String work;
    private Integer frequency;
    private Long specificDate;
    @NotBlank(message = "Vui lòng chọn thời gian")
    private Long hour;
    @NotBlank(message = "Vui lòng chọn thời gian bắt đầu")
    private Long timeStart;
    private String note;
    @NotBlank(message = "Vui lòng chọn lịch trình")
    private Integer schedulerId;
}
