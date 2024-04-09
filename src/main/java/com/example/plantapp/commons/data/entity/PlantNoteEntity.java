package com.example.plantapp.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "plant_note")
public class PlantNoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 1020)
    private String detail;
    private double height;
    private Integer quantityFlower;
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(targetEntity = UserPlantEntity.class)
    @JoinColumn(name = "user_plant_id")
    private UserPlantEntity userPlant;
}
