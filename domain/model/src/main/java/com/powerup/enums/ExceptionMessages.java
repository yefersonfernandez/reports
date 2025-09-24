package com.powerup.enums;

import lombok.Getter;

@Getter
public enum ExceptionMessages {

    UNAUTHORIZED_ACCESS("Unauthorized access: missing or invalid credentials"),
    FORBIDDEN_OPERATION("Forbidden operation: you do not have permission to perform this action"),
    S3_TEMPLATE_DOWNLOAD_FAILED("Failed to download template %s from bucket %s");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }

}