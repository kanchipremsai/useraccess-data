package com.example.useraccess.message;

import lombok.Getter;
@Getter
public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }
}