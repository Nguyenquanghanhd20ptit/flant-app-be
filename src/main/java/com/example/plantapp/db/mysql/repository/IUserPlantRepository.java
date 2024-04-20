package com.example.plantapp.db.mysql.repository;

import com.example.plantapp.commons.data.entity.UserPlantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserPlantRepository extends JpaRepository<UserPlantEntity,Integer> , JpaSpecificationExecutor<UserPlantEntity> {
}
