package com.example.plantapp.db.mysql.repository;

import com.example.plantapp.commons.data.entity.SchedulerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISchedulerRepository extends JpaRepository<SchedulerEntity,Integer> {
}
