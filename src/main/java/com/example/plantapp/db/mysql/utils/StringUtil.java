package com.example.plantapp.db.mysql.utils;

public class StringUtil {

    public static String convertStringPostgre(String value){
        String a[] = value.split("\\s+");
        String kq = "%" + String.join("%",a) +"%";
        return kq;
    }
}
