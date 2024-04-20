package com.example.plantapp.commons.data.request.scheduler.request.plant;

import com.example.plantapp.commons.data.request.scheduler.request.SearchRequest;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlantDrawChartRequest {
    private Integer numColumn;
    private Long beginTime;
    private Long endTime;
    private SearchRequest searchRequest;
}
