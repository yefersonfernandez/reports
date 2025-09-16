package com.powerup.model.report.gateways;

import com.powerup.model.report.Report;
import reactor.core.publisher.Mono;

public interface IReportRepositoryPort {
    Mono<Report> findReport();
    Mono<Report> saveReport(Report report);
}
