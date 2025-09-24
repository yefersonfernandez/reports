package com.powerup.s3.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class S3LogConstants {
    public static final String DOWNLOADING_FILE = "Downloading file {} from S3 bucket {}";
    public static final String FILE_DOWNLOADED_SUCCESS = "File {} successfully downloaded from bucket {}";
    public static final String ERROR_DOWNLOADING_FILE = "Error downloading file {} from bucket {}: {}";
}
