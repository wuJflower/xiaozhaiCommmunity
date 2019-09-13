package com.xiaozhai.community.community.exception;

public class CustomizedException extends RuntimeException {
    private String message;

    public CustomizedException(String message) {
        this.message = message;
    }
    public CustomizedException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
