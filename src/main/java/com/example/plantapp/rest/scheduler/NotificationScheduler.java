package com.example.plantapp.rest.scheduler;

import com.example.plantapp.commons.data.entity.ReminderTimeEntity;
import com.example.plantapp.commons.data.response.notification.NotificationResponse;
import com.example.plantapp.service.notification.NotificationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.plantapp.utils.DateUtils.*;

@Component
@EnableScheduling
public class NotificationScheduler {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RedisTemplate redisTemplate;
//    @Scheduled(cron = "0 0 0 * * *")
    @Scheduled(fixedDelay = 86400000)
    public void runNotificationOfDay() {
        Gson gson = new Gson();
        List<ReminderTimeEntity> reminderTimeEntities = notificationService.getNotification();
        for(ReminderTimeEntity reminderTimeEntity : reminderTimeEntities){
            String redisKey = "reminderTimeId:" + reminderTimeEntity.getId();
            String result = (String) redisTemplate.opsForValue().get(redisKey);
            if(result != null) return;
            LocalDateTime time = longToLocalDateTime(reminderTimeEntity.getHour());
            LocalDateTime currentLocalDateTime = localDateTimeNow();
            long currentTime = currentLocalDateTime.getHour() * 3600 + currentLocalDateTime.getMinute() * 60 + currentLocalDateTime.getSecond();
            long timeMinus = 30 * 60 + currentTime;
            long timeExpire = time.getHour() * 3600 + time.getMinute() * 60 + time.getSecond() - timeMinus;
            if(timeExpire <= 0) return;
            String dataJson = gson.toJson(reminderTimeEntity.getId());
            redisTemplate.opsForValue().set(redisKey, dataJson, timeExpire, TimeUnit.SECONDS);
        }
    }
}
