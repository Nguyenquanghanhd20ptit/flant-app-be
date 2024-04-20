package com.example.plantapp.commons.data.response.plant;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlantDrawCharResponse {
    private Double height;
    private Integer quantityFlower;
    private Long time;
}
