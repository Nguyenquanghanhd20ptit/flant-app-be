package com.example.plantapp.service.user;

import com.example.plantapp.commons.data.entity.UserEntity;
import com.example.plantapp.commons.data.mapper.user.UserMapper;
import com.example.plantapp.commons.data.response.user.MyInfoResponse;
import com.example.plantapp.db.mysql.repository.IUserRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyInfoService extends BaseService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    public ResponseEntity<String> getMyInfo(Integer userId){
        try {
            Optional<UserEntity> optional = userRepository.findById(userId);
            if(optional.isEmpty()){
                this.invalidMessage = "userId không tồn tại";
                return createResponseErrorValidate();
            }
            UserEntity user = optional.get();
            MyInfoResponse response = userMapper.toMyInfoResponse(user);
            return createResponseSuccess(gson.toJson(response));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
