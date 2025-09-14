package com.powerup.enums;

import lombok.Getter;

@Getter
public enum ExceptionMessages {

    UNAUTHORIZED_ACCESS("Unauthorized access: missing or invalid credentials"),
    FORBIDDEN_OPERATION("Forbidden operation: you do not have permission to perform this action"),
    FORBIDDEN_LOAN_CREATION("Forbidden operation: you can only create loans for your own account"),
    REMOTE_SERVICE_ERROR("Internal server error in remote service: %s");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }

}