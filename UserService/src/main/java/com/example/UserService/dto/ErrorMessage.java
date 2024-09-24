package com.example.UserService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private String message;
    private int code;
    public ErrorMessage(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
