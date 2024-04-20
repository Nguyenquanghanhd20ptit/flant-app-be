package com.example.plantapp.db.mysql.repository;

import com.example.plantapp.commons.data.entity.ReminderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IReminderRepository extends JpaRepository<ReminderEntity,Integer> {
    @Modifying
    @Query("delete from ReminderEntity rm where rm.scheduler.id = ?1")
    void deleteBySchedulerId(Integer schedulerId);

}
