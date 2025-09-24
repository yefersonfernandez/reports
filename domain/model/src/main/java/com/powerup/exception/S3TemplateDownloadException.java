package com.powerup.exception;

import com.powerup.enums.ExceptionStatusCode;

public class S3TemplateDownloadException extends BusinessException {
    public S3TemplateDownloadException(String message) {
        super(ExceptionStatusCode.INTERNAL_SERVER_ERROR, message);
    }
}
