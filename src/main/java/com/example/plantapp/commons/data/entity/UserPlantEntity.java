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
@Table(name = "user_plant")
public class UserPlantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String imgUrl;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(targetEntity = PlantEntity.class)
    @JoinColumn(name = "plant_id")
    private PlantEntity plant;

    @OneToMany(mappedBy = "userPlant")
    private List<PlantNoteEntity> notes;

}
