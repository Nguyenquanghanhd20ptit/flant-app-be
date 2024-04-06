package com.example.plantapp.upload.config;

import com.cloudinary.Cloudinary;
import com.example.plantapp.config.ResourceProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Data
public class CloudinaryConfig {
    @Autowired
    private ResourceProperties resourceProperties;
    private  String cloudName;
    private  String apiKey;
    private  String apiSecret;

    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name",resourceProperties.getCloudinaryName());
        config.put("api_key",resourceProperties.getCloudinaryKey());
        config.put("api_secret",resourceProperties.getCloudinarySecret());
        return new Cloudinary(config);
    }
}
