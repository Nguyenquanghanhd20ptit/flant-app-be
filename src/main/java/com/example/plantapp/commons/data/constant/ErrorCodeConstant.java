package com.example.plantapp.commons.data.constant;


import com.example.plantapp.utils.Translator;

public class ErrorCodeConstant {

    public final static String ERROR_CODE_REQUEST_INVALID_DATA = Translator.toLocale( "error.code.request.invalid_data");
    public static final String ERROR_CODE_DURING_PROCESS = Translator.toLocale("error.code.process");
    public static final String ERROR_CODE_SUCCESS = Translator.toLocale("error.code.success");
    public static final String ERROR_CODE_NOT_INFORMATION = Translator.toLocale("error.code.not_found");
    public final static String ERROR_CODE_UNKNOWN =  Translator.toLocale( "error.code.unknown");
    public final static String ERROR_CODE_TOO_MANY_REQUEST = Translator.toLocale("error.code.too_many_request");
    public final static String ERROR_CODE_TOO_LARGE_REQUEST = Translator.toLocale("error.code.too_large_request");
    public final static String ERROR_CODE_CHECKSUM = Translator.toLocale("error.code.checksum");
    public final static String ERROR_CODE_NOT_USER = Translator.toLocale("error.code.not.user");
    public final static String REST_TEMPLATE_CODE_INTERNAL_SERVER = Translator.toLocale("rest.template.5xx.code");
    public final static String REST_TEMPLATE_CODE_BAD_REQUEST = Translator.toLocale("rest.template.4xx.code");
    public final static String REST_TEMPLATE_CODE_SERVICE_UNAVAILABLE = Translator.toLocale("rest.template.Unknown.code");
    public final static String REST_TEMPLATE_CODE_READ_TIMEOUT = Translator.toLocale("rest.template.read-timeout.code");
    public final static String REST_TEMPLATE_CODE_CONNECT_TIMEOUT = Translator.toLocale("rest.template.connect-timeout.code");

}
