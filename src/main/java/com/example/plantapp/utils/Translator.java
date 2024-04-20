package com.example.plantapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {

    private static MessageSource messageSource;

    public static void setResource(MessageSource desMessageSource) {
        messageSource = desMessageSource;
    }

    @Autowired
    Translator(MessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    public static String toLocale(String msg) {
        return messageSource.getMessage(msg, null, Locale.ENGLISH);
    }
}
