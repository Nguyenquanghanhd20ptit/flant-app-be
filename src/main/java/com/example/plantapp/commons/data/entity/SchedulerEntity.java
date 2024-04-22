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
@Table(name = "scheduler")
public class SchedulerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = PlantEntity.class)
    @JoinColumn(name = "plant_id")
    private PlantEntity plant;
    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "created_by")
    private UserEntity createdBy;
    @OneToMany(mappedBy = "scheduler")
    private List<ReminderEntity> reminders;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
