package com.example.plantapp.db.mysql.repository;

import com.example.plantapp.commons.data.entity.SchedulerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISchedulerRepository extends JpaRepository<SchedulerEntity,Integer> {

    @Query("select s from SchedulerEntity s where s.plant.id = ?1 and s.createdBy.id = ?2")
    Optional<SchedulerEntity> findByPlantAndUser(Integer plantId, Integer userId);
}
