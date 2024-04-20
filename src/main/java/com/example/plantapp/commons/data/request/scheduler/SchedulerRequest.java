package com.example.plantapp.commons.data.request.scheduler;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SchedulerRequest {
    private Integer plantId;
    private Integer createdBy;
}
