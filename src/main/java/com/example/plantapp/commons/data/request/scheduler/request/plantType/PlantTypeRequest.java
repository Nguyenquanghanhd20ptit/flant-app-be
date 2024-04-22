package com.example.plantapp.commons.data.request.scheduler.request.plantType;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;


@Data
@Accessors(chain = true)
public class PlantTypeRequest {
    @NotBlank(message = "Tên loại cây không được để chống")
    private String name;
    private String imageUrl;
    private String description;
}
