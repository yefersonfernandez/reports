package com.powerup.s3.utils;

import lombok.experimental.UtilityClass;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class S3TemplateUtils  {
    public static String toUtf8String(ByteBuffer buffer) {
        ByteBuffer duplicate = buffer.asReadOnlyBuffer();
        byte[] bytes = new byte[duplicate.remaining()];
        duplicate.get(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }
}
