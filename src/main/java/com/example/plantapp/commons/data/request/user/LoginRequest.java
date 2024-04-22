package com.example.plantapp.commons.data.request.user;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginRequest {
    private String email;
    private String password;
}
