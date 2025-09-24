package com.powerup.s3.config.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.s3")
public record S3ConnectionProperties(
        String region,
        String bucketName) {
}
