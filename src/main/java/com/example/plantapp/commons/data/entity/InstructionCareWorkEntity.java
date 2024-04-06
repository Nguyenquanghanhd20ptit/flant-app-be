package com.example.plantapp.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "instruction_care_work")
public class InstructionCareWorkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = CareWorkEntity.class)
    @JoinColumn(name = "care_work_id")
    private CareWorkEntity careWork;

    @ManyToOne(targetEntity = InstructionCareEntity.class)
    @JoinColumn(name = "instruction_care_id")
    private InstructionCareEntity instructionCare;

    private String instructionHtml;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
