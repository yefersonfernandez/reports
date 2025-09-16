package com.powerup.api.report;

import com.powerup.api.dto.response.ReportResponseDto;
import com.powerup.api.mapper.IReportMapper;
import com.powerup.usecase.report.ReportUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportHandler {

    private final ReportUseCase reportUseCase;
    private final IReportMapper reportMapper;

    public Mono<ServerResponse> getReports(ServerRequest request) {
        log.info("Fetching report of approved loans");

        return reportUseCase.getReport()
                .map(reportMapper::toReportResponseDto)
                .defaultIfEmpty(new ReportResponseDto(0L, BigDecimal.ZERO))
                .flatMap(reportResponseDto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(reportResponseDto)
                )
                .doOnError(e -> log.error("Error fetching report of approved loans", e));
    }
}
