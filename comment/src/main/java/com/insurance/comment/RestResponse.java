package com.insurance.comment;

public class RestResponse {
    private Object message;
    private String code;

    public RestResponse(String code, Object message) {
        this.message = message;
        this.code = code;
    }

    public RestResponse() {
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
