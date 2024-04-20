package com.example.plantapp.commons.data.response.plant;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class PlantShortResponse {
    private Integer id;
    private String name;
    private String description;
    private String image_url;
    private String origin;
    private LocalDateTime createdAt;
}
