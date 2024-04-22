package com.example.plantapp.commons.data.request.user;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.example.plantapp.commons.data.constant.RegexConstant.EMAIL_PATTERN;
import static com.example.plantapp.commons.data.constant.RegexConstant.PHONE_NUMBER_PATTERN;

@Data
@Accessors(chain = true)
public class RegisterRequest {
    @NotBlank(message = "Thông tin username không được để trống")
    @Length(min = 6, message = "Tên đăng nhập phải lớn hơn hoặc bằng 6 kí tự")
    private String username;
    @NotBlank(message = "Thông tin password không được để trống")
    @Length(min = 8, message = "Mật khẩu phải lớn hơn hoặc bằng 8 kí tự")
    private String password;
    @NotBlank(message = "Thông tin confirmPassword không được để trống")
    private String confirmPassword;
    @NotBlank(message = "Thông tin fullName không được để trống")
    private String fullName;
    @NotBlank(message = "Thông tin email không được để trống")
    @Pattern(regexp = EMAIL_PATTERN,message = "Email không hợp lệ")
    private String email;
    @NotBlank(message = "Thông tin address không được để trống")
    private String address;
    @NotBlank(message = "Thông tin phoneNumber không được để trống")
    @Pattern(regexp = PHONE_NUMBER_PATTERN,message = "Số điện thoại không hợp lệ")
    private String phoneNumber;
}
