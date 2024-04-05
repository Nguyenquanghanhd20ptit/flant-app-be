package com.example.plantapp.commons.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Struct;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "plant")
public class PlantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(length = 1000)
    private String description;
    private String image_urls;
    private String origin;
}
