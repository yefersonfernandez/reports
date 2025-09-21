package com.powerup.emailses;

import com.powerup.emailses.config.SesProperties;
import com.powerup.model.report.EmailReport;
import com.powerup.model.report.gateways.IEmailSenderPort;
import com.powerup.emailses.constants.EmailLogConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.ses.SesAsyncClient;

import static com.powerup.emailses.constants.EmailLogConstants.EMAIL_SENT_SUCCESS;
import static com.powerup.emailses.constants.EmailLogConstants.ERROR_SENDING_EMAIL;
import static com.powerup.emailses.constants.EmailLogConstants.SENDING_EMAIL;
import static com.powerup.emailses.utils.SesEmailRequestBuilder.buildSendEmailRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class SesEmailSenderAdapter implements IEmailSenderPort {

    private final SesProperties sesProperties;
    private final SesAsyncClient sesClient;

    @Override
    public Mono<Void> sendEmail(EmailReport report) {
        log.info(SENDING_EMAIL, sesProperties.destinationEmail(), report);

        return Mono.fromFuture(() -> sesClient.sendEmail(buildSendEmailRequest(sesProperties, report)))
                .doOnSuccess(response -> log.info(EMAIL_SENT_SUCCESS, sesProperties.destinationEmail()))
                .doOnError(error -> log.error(ERROR_SENDING_EMAIL, error.getMessage(), error))
                .then();
    }
}
