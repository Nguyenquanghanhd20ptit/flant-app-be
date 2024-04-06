package com.example.plantapp.upload.service;

import com.cloudinary.Cloudinary;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class CloudinaryServiceImpl implements ICloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private Gson gson = new Gson();

    @Override
    public String uploadCloudinary(MultipartFile multipartFile) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("public_id", UUID.randomUUID().toString());
            String url = cloudinary.uploader()
                    .upload(multipartFile.getBytes(), params)
                    .get("url")
                    .toString();
            return  url;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<String> uploadListImageCloudinary(MultipartFile[] multipartFiles) {
        try {
           List<String> urls = new ArrayList<>();
            for (MultipartFile file : multipartFiles){
                Map<String, Object> params = new HashMap<>();
                params.put("public_id", UUID.randomUUID().toString());
                 String url = cloudinary.uploader()
                        .upload(file.getBytes(), params)
                        .get("url")
                        .toString();
                 urls.add(url);
            }
            return urls;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    @Override
    public String uploadVideoCloudinary(MultipartFile multipartFile) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("resource_type", "video");
            params.put("public_id", UUID.randomUUID().toString());

            String url = cloudinary.uploader().upload(multipartFile.getBytes(), params)
                    .get("url")
                    .toString();
            return url;
        } catch (Exception e) {
            return null;
        }
    }
}
