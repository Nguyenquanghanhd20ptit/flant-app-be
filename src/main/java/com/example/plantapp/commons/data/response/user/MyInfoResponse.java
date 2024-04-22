package com.example.plantapp.commons.data.response.user;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MyInfoResponse {
    private Integer id;
    private String username;
    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;
}
