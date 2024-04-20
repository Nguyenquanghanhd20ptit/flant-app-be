package com.example.plantapp.commons.data.request.plant;

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
    private String image_url;
    private String origin;
    private String guide;
    private String water;
    private String fertilizer;
    private String light;
    private String soil;
    private String tempAndHumidity;
    private String pruning_month;
    private String grow_level;
    private String cycle;
    private String care_level;
}
