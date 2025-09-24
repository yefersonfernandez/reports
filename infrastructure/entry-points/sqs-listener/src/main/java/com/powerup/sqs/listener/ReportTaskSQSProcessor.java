package com.powerup.sqs.listener;

import com.powerup.usecase.report.ReportUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.function.Function;

import static com.powerup.sqs.listener.constants.SQSLogConstants.ERROR_SENDING_EMAIL;
import static com.powerup.sqs.listener.constants.SQSLogConstants.RECEIVED_MESSAGE;

@Slf4j
@Service("reportTaskQueueProcessor")
@RequiredArgsConstructor
public class ReportTaskSQSProcessor implements Function<Message, Mono<Void>> {

    private final ReportUseCase reportUseCase;

    @Value("${aws.dynamodb.emailTemplates.emailTemplatePk}")
    private String emailTemplatePk;

    @Override
    public Mono<Void> apply(Message message) {
        log.info(RECEIVED_MESSAGE, message.body());

        return reportUseCase.sendReportEmail(emailTemplatePk)
                .doOnError(error -> log.error(ERROR_SENDING_EMAIL, error.getMessage(), error))
                .then();
    }
}

