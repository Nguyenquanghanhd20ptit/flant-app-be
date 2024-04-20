package com.example.plantapp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

import static java.time.temporal.ChronoField.INSTANT_SECONDS;

public class DateUtils {

    public static String getCurrentDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = Calendar.getInstance().getTime();
        return dateFormat.format(date);
    }

    public static LocalDateTime longToLocalDateTime(Long time) {
        return Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Long localDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime == null ? null :
                localDateTime.atZone(ZoneId.systemDefault()).getLong(INSTANT_SECONDS) * 1000;
    }
    public static LocalDate longToLocalDate(Long time) {
        return Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Long localTimeToLong(LocalTime localTime) {
        return localTime == null ? null :
                localTime.getLong(INSTANT_SECONDS) * 1000;
    }
    public static LocalTime longToLocalTime(Long localTime) {
        return Instant.ofEpochMilli(localTime).atZone(ZoneId.systemDefault()).toLocalTime();
    }
    public static Long longLocalDateTimeNow(){
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).getLong(INSTANT_SECONDS) * 1000;
    }

    public static LocalDateTime localDateTimeNow(){
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime localTimeToLocalDateTime(LocalTime localTime){
        LocalDate currentDate = LocalDate.now();
        LocalDateTime currentDateTime = LocalDateTime.of(currentDate, localTime);
        return currentDateTime;
    }

}
