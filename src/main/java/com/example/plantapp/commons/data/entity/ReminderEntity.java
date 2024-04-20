package com.example.plantapp.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "reminder")
public class ReminderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String work;
    private Integer frequency;
    private Long specificDate;
    private Long hour;
    private Long timeStart;
    private String period;
    private String note;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne(targetEntity = SchedulerEntity.class)
    @JoinColumn(name = "scheduler_id")
    private SchedulerEntity scheduler;
}
