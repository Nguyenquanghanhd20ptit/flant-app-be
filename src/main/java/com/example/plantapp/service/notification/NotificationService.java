package com.example.plantapp.service.notification;

import com.example.plantapp.commons.data.entity.ReminderTimeEntity;
import com.example.plantapp.db.mysql.repository.IReminderTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.plantapp.utils.DateUtils.localDateTimeNow;
import static com.example.plantapp.utils.DateUtils.localDateTimeToLong;

@Service
public class NotificationService {
    @Autowired
    private IReminderTimeRepository reminderTimeRepository;

    public List<ReminderTimeEntity> getNotification() {
        try {
            LocalDateTime time = localDateTimeNow();
            LocalDateTime beginTime = LocalDateTime.of(time.getYear(),
                    time.getMonth(), time.getDayOfMonth(), 0, 0, 0, 0);
            LocalDateTime endTime = beginTime.plusDays(1);
           return reminderTimeRepository
                    .getReminderSpecificDate(localDateTimeToLong(beginTime), localDateTimeToLong(endTime));
        } catch (Exception e) {
          return new ArrayList<>();
        }
    }
}
