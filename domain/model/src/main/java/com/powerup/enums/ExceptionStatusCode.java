package com.powerup.enums;

import lombok.Getter;

@Getter
public enum ExceptionStatusCode {

    UNAUTHORIZED(401),
    FORBIDDEN(403),
    INTERNAL_SERVER_ERROR(500);

    private final int statusCode;

    ExceptionStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}