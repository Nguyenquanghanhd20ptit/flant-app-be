package com.example.plantapp.service.authentication;

import com.example.plantapp.commons.data.entity.UserEntity;
import com.example.plantapp.commons.data.mapper.user.UserMapper;
import com.example.plantapp.commons.data.request.user.LoginRequest;
import com.example.plantapp.commons.data.response.user.MyInfoResponse;
import com.example.plantapp.db.mysql.repository.IUserRepository;
import com.example.plantapp.service.BaseService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_NOT_INFORMATION;

@Service
public class LoginService extends BaseService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public ResponseEntity<String> login(LoginRequest request){
        try {
            List<UserEntity> userEntities = userRepository.findByEmail(request.getEmail());
            if(userEntities.isEmpty()){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"Sai email hoặc mật khẩu");
            }
            UserEntity user = userEntities.get(0);
            Boolean isValidPass = BCrypt.checkpw(request.getPassword(),user.getPassword());
            if(!isValidPass){
                return createResponseError(ERROR_CODE_NOT_INFORMATION,"Sai email hoặc mật khẩu");
            }
            MyInfoResponse response = userMapper.toMyInfoResponse(user);
            return createResponseSuccess(gson.toJson(response));
        }catch (Exception e){
            return createResponseException(e);
        }
    }
}
