package com.example.plantapp.db.mysql.repository;

import com.example.plantapp.commons.data.entity.ReminderEntity;
import com.example.plantapp.commons.data.entity.ReminderTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReminderTimeRepository extends JpaRepository<ReminderTimeEntity,Integer> {

    @Query("select rm from ReminderTimeEntity rm where rm.specificDate >= ?1 and rm.specificDate < ?2")
    List<ReminderTimeEntity> getReminderSpecificDate(Long beginTime, Long endTime);
}
