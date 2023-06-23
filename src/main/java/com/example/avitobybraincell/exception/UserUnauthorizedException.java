package com.example.avitobybraincell.exception;

public class UserUnauthorizedException  extends RuntimeException {
    public UserUnauthorizedException(String message) {
        super(message);
    }
}
