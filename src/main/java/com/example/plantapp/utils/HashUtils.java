package com.example.plantapp.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashUtils {
    public  static String md5(String text){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder result = new StringBuilder();
            for(byte b : hashInBytes){
                result.append(String.format("%02x",b));
            }
            return result.toString();
        }catch (Exception e){
            return null;
        }
    }
}
