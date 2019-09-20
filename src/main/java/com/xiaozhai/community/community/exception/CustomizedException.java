package com.xiaozhai.community.community.exception;

public class CustomizedException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomizedException(CustomizeErrorCode customizeErrorCode) {
        this.message = customizeErrorCode.getMessage();
        this.code = customizeErrorCode.getCOde();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return code;
    }
}
