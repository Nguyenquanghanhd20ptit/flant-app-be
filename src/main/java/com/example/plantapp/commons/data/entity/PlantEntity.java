package com.example.plantapp.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "plant")
public class PlantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(length = 1020)
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
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(targetEntity = PlantTypeEntity.class)
    @JoinColumn(name = "plant_type_id")
    private PlantTypeEntity plantType;

    @OneToMany(mappedBy = "plant")
    private List<UserPlantEntity> userPlants;

}
