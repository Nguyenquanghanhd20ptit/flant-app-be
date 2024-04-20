package com.example.plantapp.service;

import com.example.plantapp.commons.data.response.DataResponse;
import com.example.plantapp.config.gson.LocalDateTimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.*;
import static com.example.plantapp.commons.data.constant.ErrorCodeConstant.ERROR_CODE_DURING_PROCESS;
import static com.example.plantapp.commons.data.constant.ErrorMessageConstant.ERROR_MESSAGE_DURING_PROCESS;
import static com.example.plantapp.commons.data.constant.ErrorMessageConstant.ERROR_MESSAGE_SUCCESS;

public abstract class BaseService {
    protected final Gson gsonDateFormat = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,new LocalDateTimeSerializer())
            .create();
    protected final Gson gson = new Gson();
    protected  String invalidMessage = "invalid request!";
    protected  ResponseEntity<String> createResponseError(String errorCode, String errorMessage){
        DataResponse dataResponse = new DataResponse()
                .setErrorCode(errorCode)
                .setErrorMessage(errorMessage);
        return ResponseEntity.ok(gson.toJson(dataResponse));
    }
    protected  ResponseEntity<String> createResponseErrorValidate(){
        DataResponse dataResponse = new DataResponse()
                .setErrorCode(ERROR_CODE_REQUEST_INVALID_DATA)
                .setErrorMessage(invalidMessage);
        return ResponseEntity.ok(gson.toJson(dataResponse));
    }

    protected  ResponseEntity<String> createResponseSuccess(String data){
        DataResponse dataResponse = new DataResponse()
                .setErrorCode(ERROR_CODE_SUCCESS)
                .setErrorMessage(ERROR_MESSAGE_SUCCESS)
                .setData(data);
        return ResponseEntity.ok(gson.toJson(dataResponse));
    }
    protected  ResponseEntity<String> createResponseException(Exception e){
        DataResponse dataResponse = new DataResponse()
                .setErrorCode(ERROR_CODE_DURING_PROCESS)
                .setErrorMessage(ERROR_MESSAGE_DURING_PROCESS);
        return ResponseEntity.ok(gson.toJson(dataResponse));
    }

    protected  ResponseEntity<String> createResponseErrorDuringProcess(){
        DataResponse dataResponse = new DataResponse()
                .setErrorCode(ERROR_CODE_DURING_PROCESS)
                .setErrorMessage(ERROR_MESSAGE_DURING_PROCESS);
        return ResponseEntity.ok(gson.toJson(dataResponse));
    }
}
