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
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(targetEntity = PlantTypeEntity.class)
    @JoinColumn(name = "plant_type_id")
    private PlantTypeEntity plantType;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "created_by")
    private PlantTypeEntity createdBy;

    @OneToMany(mappedBy = "plant")
    private List<UserPlantEntity> userPlants;

    @OneToMany(mappedBy = "plant")
    private List<InstructionCareEntity> instructionCares;

}
