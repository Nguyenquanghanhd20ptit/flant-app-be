package com.example.plantapp.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "reminder_time")
public class ReminderTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long specificDate;
    private Long hour;
    @ManyToOne(targetEntity = ReminderEntity.class)
    @JoinColumn(name = "reminder_id")
    private ReminderEntity reminder;

    @ManyToOne(targetEntity = PlantEntity.class)
    @JoinColumn(name = "plant_id")
    private PlantEntity plant;
}
