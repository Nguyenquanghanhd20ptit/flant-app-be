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
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "created_by")
    private List<SchedulerEntity> schedulers;
    @OneToMany(mappedBy = "user")
    private List<UserPlantEntity> plants;
}
