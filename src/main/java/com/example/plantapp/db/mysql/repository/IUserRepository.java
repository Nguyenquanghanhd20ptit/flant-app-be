package com.example.plantapp.db.mysql.repository;

import com.example.plantapp.commons.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,Integer> {

    @Query("select u from UserEntity u where u.email = ?1")
    List<UserEntity> findByEmail(String email);

    @Query("select u from UserEntity  u where  u.phoneNumber = ?1")
    List<UserEntity> findByPhoneNumber(String phoneNumber);

    @Query("select  u from  UserEntity  u where  u.username = ?1")
    List<UserEntity> findByUsername(String username);
}
