package com.example.plantapp.commons.data.request.scheduler.request.plant;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;


@Data
@Accessors(chain = true)
public class PlantRequest {
    private Integer plantTypeId;
    @NotBlank(message = "Tên cây không được để chống")
    private String name;
    private String description;
    private String imageUrl;
    private String origin;
    private String guide;
    private String water;
    private String fertilizer;
    private String light;
    private String soil;
    private String tempAndHumidity;
    private String pruningMonth;
    private String growLevel;
    private String cycle;
    private String careLevel;
}
