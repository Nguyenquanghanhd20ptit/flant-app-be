package com.example.plantapp.commons.data.constant;


import com.example.plantapp.utils.Translator;

public class ErrorMessageConstant {
    public static final String ERROR_MESSAGE_INVALID_DATA = Translator.toLocale( "error.message.invalid-data");
    public static final String ERROR_MESSAGE_DURING_PROCESS = Translator.toLocale("error.message.process");
    public static final String ERROR_MESSAGE_USER_NOT_EXIST = Translator.toLocale("error.message.not.user");
    public static final String ERROR_MESSAGE_SUCCESS = Translator.toLocale("error.message.success");
    public final static String ERROR_MESSAGE_UNKNOWN = Translator.toLocale("error.message.unknown");
    public final static String ERROR_MESSAGE_TOO_MANY_REQUEST = Translator.toLocale("error.message.too_many_request");
    public final static String ERROR_MESSAGE_TOO_LARGE_REQUEST = Translator.toLocale("error.message.too_large_request");
    public final static String ERROR_MESSAGE_CHECKSUM = Translator.toLocale("error.message.checksum");
    public final static String ERROR_MESSAGE_NOT_INFORMATION = Translator.toLocale("error.message.not-found");
    public final static String REST_TEMPLATE_MESSAGE_INTERNAL_SERVER = Translator.toLocale("rest.template.5xx");
    public final static String REST_TEMPLATE_MESSAGE_BAD_REQUEST = Translator.toLocale("rest.template.4xx");
    public final static String REST_TEMPLATE_MESSAGE_SERVICE_UNAVAILABLE = Translator.toLocale("rest.template.Unknown");
    public final static String REST_TEMPLATE_MESSAGE_READ_TIMEOUT = Translator.toLocale("rest.template.read-timeout");
    public final static String REST_TEMPLATE_MESSAGE_CONNECT_TIMEOUT = Translator.toLocale("rest.template.connect-timeout");

}
