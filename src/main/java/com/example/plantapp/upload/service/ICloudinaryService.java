package com.example.plantapp.upload.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICloudinaryService {
    String uploadCloudinary(MultipartFile multipartFile);

    List<String> uploadListImageCloudinary(MultipartFile[] multipartFiles);

    String uploadVideoCloudinary(MultipartFile multipartFile);
}
