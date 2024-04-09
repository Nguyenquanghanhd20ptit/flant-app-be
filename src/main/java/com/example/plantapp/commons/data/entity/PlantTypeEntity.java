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
@Table(name = "plant_type")
public class PlantTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;
    @Column(length = 1000)
    private String description;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "plantType")
    private List<PlantEntity> plants;
}
