package com.example.plantapp.config;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Configuration
public class RedisConfig {
    @Autowired
    private final ResourceProperties resourceProperties;

    public RedisConfig(ResourceProperties resourceProperties) {
        this.resourceProperties = resourceProperties;
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        final SocketOptions socketOptions = SocketOptions
                .builder().connectTimeout(Duration.ofMillis(resourceProperties.getTimeout())).build();
        final ClientOptions clientOptions = ClientOptions
                .builder().socketOptions(socketOptions).build();
        LettuceClientConfiguration configuration = LettuceClientConfiguration
                .builder().clientOptions(clientOptions).build();
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration
                (resourceProperties.getRedisHost(),resourceProperties.getRedisPost());
        redisStandaloneConfiguration.setPassword(resourceProperties.getRedisPassword());
        final LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory
                (redisStandaloneConfiguration,configuration);
        lettuceConnectionFactory.setValidateConnection(true);
        return lettuceConnectionFactory;
    }

    @Bean
    @Primary
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return  redisTemplate;
    }
}
