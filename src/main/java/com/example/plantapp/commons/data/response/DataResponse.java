package com.example.plantapp.commons.data.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DataResponse {
    private String errorCode;
    private String errorMessage;
    private String data;
}
