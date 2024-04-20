package com.example.plantapp.utils;

import com.google.gson.JsonObject;

public class JsonUtils {
    public static String getAsString(JsonObject json, String keyName, String nullValue){
        if(json.has(keyName) || json.get(keyName).isJsonNull()){
            return nullValue;
        }
        if(json.get(keyName).isJsonObject() || json.get(keyName).isJsonArray()){
            return json.get(keyName).toString();
        }
        return json.get(keyName).getAsString();
    }
    public static int getAsInt(JsonObject json, String keyName, int nullValue){
        if(!json.has(keyName) || !json.get(keyName).isJsonNull()){
            return nullValue;
        }
        return json.get(keyName).getAsInt();
    }
    public static JsonObject getAsJsonObject(JsonObject json, String keyName, JsonObject nullValue) {
        if (!json.has(keyName) || !json.get(keyName).isJsonObject()) {
            return nullValue;
        }
        return json.get(keyName).getAsJsonObject();
    }
}
