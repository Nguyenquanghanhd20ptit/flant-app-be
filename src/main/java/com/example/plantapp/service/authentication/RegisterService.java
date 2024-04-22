package com.example.plantapp.service.authentication;

import com.example.plantapp.commons.data.entity.UserEntity;
import com.example.plantapp.commons.data.mapper.user.UserMapper;
import com.example.plantapp.commons.data.request.user.RegisterRequest;
import com.example.plantapp.db.mysql.repository.IUserRepository;
import com.example.plantapp.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class RegisterService extends BaseService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    public ResponseEntity<String> register( RegisterRequest request){
        try {
            if (!validateReq(request)){
                return createResponseErrorValidate();
            }
            UserEntity user = userMapper.toEntity(request);
            UserEntity save = userRepository.save(user);
            if(ObjectUtils.isEmpty(save)){
                return createResponseErrorDuringProcess();
            }
            return createResponseSuccess(gson.toJson("Đăng kí thành công"));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
    public Boolean validateReq(RegisterRequest request){
        if(!request.getPassword().equals(request.getConfirmPassword())){
            this.invalidMessage = "Mật khẩu không khớp";
            return false;
        }
        List<UserEntity> userEmails = userRepository.findByEmail(request.getEmail());
        if(!userEmails.isEmpty()){
            this.invalidMessage = "Email đã tồn tại";
            return false;
        }
        List<UserEntity> userPhones = userRepository.findByPhoneNumber(request.getPhoneNumber());
        if(!userPhones.isEmpty()){
            this.invalidMessage = "Số điện thoại đã tồn tại";
            return false;
        }
        List<UserEntity> usernames = userRepository.findByUsername(request.getUsername());
        if(!usernames.isEmpty()){
            this.invalidMessage = "Username đã tồn tại";
            return false;
        }
        return true;
    }
}
