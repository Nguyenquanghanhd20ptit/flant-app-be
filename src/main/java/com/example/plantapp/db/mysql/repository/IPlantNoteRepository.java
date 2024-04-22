package com.example.plantapp.db.mysql.repository;

import com.example.plantapp.commons.data.entity.PlantNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlantNoteRepository extends JpaRepository<PlantNoteEntity,Integer> , JpaSpecificationExecutor<PlantNoteEntity> {
}
