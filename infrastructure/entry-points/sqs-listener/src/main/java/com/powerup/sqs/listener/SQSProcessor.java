package com.powerup.sqs.listener;

import com.powerup.sqs.listener.dto.LoanApprovedDto;
import com.powerup.sqs.listener.mapper.ILoanApprovedMapper;
import com.powerup.usecase.report.ReportUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class SQSProcessor implements Function<Message, Mono<Void>> {

    private final ReportUseCase reportUseCase;
    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper;
    private final ILoanApprovedMapper mapper;

    @Override
    public Mono<Void> apply(Message message) {
        log.info("Received SQS message: {}", message.body());

        return Mono.fromCallable(() -> objectMapper.readValue(message.body(), LoanApprovedDto.class))
                .map(mapper::toDomain)
                .flatMap(reportUseCase::updateReportOnLoanApproved)
                .doOnError(error -> log.error("Error processing SQS message: {}", message.body(), error))
                .then();
    }
}

