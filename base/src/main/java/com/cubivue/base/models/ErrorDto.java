package com.cubivue.base.models;

public class ErrorDto {

    public ErrorDto(int httpStatus, int serverCode, String message) {
        this.httpStatus = httpStatus;
        this.serverCode = serverCode;
        this.message = message;
    }

    public int httpStatus;
    public int serverCode;
    public String message;
}