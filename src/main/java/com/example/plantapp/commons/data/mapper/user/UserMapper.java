package com.example.plantapp.commons.data.mapper.user;

import com.example.plantapp.commons.data.entity.UserEntity;
import com.example.plantapp.commons.data.request.user.RegisterRequest;
import com.example.plantapp.commons.data.response.user.MyInfoResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mindrot.jbcrypt.BCrypt;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Mapping(target = "password",ignore = true)
    public abstract UserEntity toEntity(RegisterRequest request);
    @AfterMapping
    public void map(@MappingTarget UserEntity entity, RegisterRequest request){
        entity.setPassword(BCrypt.hashpw(request.getPassword(),BCrypt.gensalt()));
    }

    public abstract MyInfoResponse toMyInfoResponse(UserEntity user);
}
