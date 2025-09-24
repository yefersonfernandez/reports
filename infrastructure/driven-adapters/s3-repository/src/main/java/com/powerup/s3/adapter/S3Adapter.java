package com.powerup.s3.adapter;

import com.powerup.enums.ExceptionMessages;
import com.powerup.exception.S3TemplateDownloadException;
import com.powerup.model.s3.gateways.IS3TemplatePort;
import com.powerup.s3.config.model.S3ConnectionProperties;
import com.powerup.s3.constants.S3LogConstants;
import com.powerup.s3.operations.S3Operations;
import com.powerup.s3.utils.S3TemplateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static com.powerup.s3.constants.S3LogConstants.ERROR_DOWNLOADING_FILE;
import static com.powerup.s3.constants.S3LogConstants.FILE_DOWNLOADED_SUCCESS;

@Repository
@RequiredArgsConstructor
@Slf4j
public class S3Adapter implements IS3TemplatePort {

    private final S3Operations s3Operations;
    private final S3ConnectionProperties s3Properties;

    @Override
    public Mono<String> getTemplateHtml(String key) {
        log.info(S3LogConstants.DOWNLOADING_FILE, key, s3Properties.bucketName());

        return s3Operations.getObject(s3Properties.bucketName(), key)
                .map(S3TemplateUtils::toUtf8String)
                .collect(StringBuilder::new, StringBuilder::append)
                .map(StringBuilder::toString)
                .doOnSuccess(response -> log.info(FILE_DOWNLOADED_SUCCESS, key, s3Properties.bucketName()))
                .doOnError(error -> log.error(ERROR_DOWNLOADING_FILE, key, s3Properties.bucketName(), error.getMessage()))
                .onErrorMap(error -> new S3TemplateDownloadException(
                        ExceptionMessages.S3_TEMPLATE_DOWNLOAD_FAILED.format(key, s3Properties.bucketName())
                ));
    }

}
