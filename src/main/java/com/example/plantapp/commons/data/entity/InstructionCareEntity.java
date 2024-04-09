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
@Table(name = "instruction_care")
public class InstructionCareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = PlantEntity.class)
    @JoinColumn(name = "plant_id")
    private PlantEntity plant;

    @ManyToOne(targetEntity = CareTypeEntity.class)
    @JoinColumn(name = "care_type_id")
    private CareTypeEntity careType;

    @OneToMany(mappedBy = "instructionCare")
    private List<InstructionCareWorkEntity> instructionCareWorks;
    @CreationTimestamp
    private LocalDateTime createdAt;


}
