package com.example.plantapp.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class ResourceProperties {
    private final int timeout;
    private final int redisTimeout;
    private final String redisHost;
    private final int redisPost;
    private final String redisPassword;
    private final String cloudinaryName;
    private final String cloudinaryKey;
    private final String cloudinarySecret;
    public ResourceProperties(@Value("${api.timeout}") int timeout,
                              @Value("${spring.redis.timeout}") int redisTimeout,
                              @Value("${spring.redis.host}") String redisHost,
                              @Value("${spring.redis.port}") int redisPost,
                              @Value("${spring.redis.password}") String redisPassword,
                              @Value("${upload.cloudinary.cloud-name}") String cloudinaryName,
                              @Value("${upload.cloudinary.api-key}") String cloudinaryKey,
                              @Value("${upload.cloudinary.api-secret}") String cloudinarySecret){
        this.redisTimeout = redisTimeout;
        this.timeout = timeout;
        this.redisHost = redisHost;
        this.redisPost = redisPost;
        this.redisPassword = redisPassword;
        this.cloudinaryName = cloudinaryName;
        this.cloudinaryKey = cloudinaryKey;
        this.cloudinarySecret = cloudinarySecret;
    }
}
