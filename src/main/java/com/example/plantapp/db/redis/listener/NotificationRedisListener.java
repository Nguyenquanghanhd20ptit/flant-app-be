package com.example.plantapp.db.redis.listener;

import com.example.plantapp.commons.data.entity.ReminderTimeEntity;
import com.example.plantapp.commons.data.mapper.plant.PlantShortMapper;
import com.example.plantapp.commons.data.mapper.reminder.ReminderMapper;
import com.example.plantapp.commons.data.response.notification.NotificationResponse;
import com.example.plantapp.commons.data.response.plant.PlantShortResponse;
import com.example.plantapp.commons.data.response.reminder.ReminderResponse;
import com.example.plantapp.db.mysql.repository.IReminderTimeRepository;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NotificationRedisListener implements MessageListener {
    @Autowired
    private IReminderTimeRepository reminderTimeRepository;
    @Autowired
    private PlantShortMapper plantShortMapper;
    @Autowired
    private ReminderMapper reminderMapper;
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            byte[] body = message.getBody();
            String key = new String(body);
            if (key.startsWith("reminderTimeId:")) {
                int index = key.indexOf(":");
                String reminderTimeId;
                if (index != -1 && index < key.length() - 1) {
                    reminderTimeId = key.substring(index + 1);
                } else return;
                System.out.println(reminderTimeId);
                Optional<ReminderTimeEntity> opt = reminderTimeRepository.findById(Integer.parseInt(reminderTimeId));
                if(opt.isEmpty()){
                    return;
                }
                ReminderTimeEntity reminderTimeEntity = opt.get();
                reminderTimeRepository.deleteById(Integer.valueOf(reminderTimeId));
                PlantShortResponse plantShortResponse = plantShortMapper.toResponse(reminderTimeEntity.getPlant());
                ReminderResponse reminderResponse = reminderMapper.toResponse(reminderTimeEntity.getReminder());
                NotificationResponse notificationResponse = new NotificationResponse()
                        .setPlant(plantShortResponse)
                        .setReminder(reminderResponse);
                // dua ra thong bao
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
