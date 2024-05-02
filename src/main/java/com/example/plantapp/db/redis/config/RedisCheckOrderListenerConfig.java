package com.example.plantapp.db.redis.config;

import com.example.plantapp.db.redis.listener.NotificationRedisListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisCheckOrderListenerConfig {

    @Autowired
    private NotificationRedisListener notificationRedisListener;
    @Autowired
    private RedisTemplate redisTemplate;
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        ChannelTopic channelTopic = new ChannelTopic("__keyevent@0__:expired");
        container.addMessageListener(notificationRedisListener, channelTopic);
        return container;
    }
}
