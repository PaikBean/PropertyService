package com.propertyservice.propertyservice.domain.common;

import lombok.Getter;

@Getter
public class Response<T> {

    private ResponseCode responseCode;
    private T data;
    private String message;
    private String code;

    public Response(ResponseCode responseCode, String message, String code) {
        this.responseCode = responseCode;
        this.message = message;
        this.code = code;
    }

    public Response(ResponseCode responseCode, T data, String code) {
        this.responseCode = responseCode;
        this.data = data;
        this.code = code;
    }
}