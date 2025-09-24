package com.powerup.dynamodb.adapters.report;

import com.powerup.dynamodb.entity.ReportEntity;
import com.powerup.dynamodb.helper.TemplateAdapterOperations;
import com.powerup.model.report.Report;
import com.powerup.model.report.gateways.IReportRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;

@Slf4j
@Repository
public class ReportRepositoryAdapter extends TemplateAdapterOperations<Report, String, ReportEntity>
        implements IReportRepositoryPort {

    private static final String REPORT_PK = "REPORT";

    public ReportRepositoryAdapter(DynamoDbEnhancedAsyncClient client, ObjectMapper mapper) {
        super(client, mapper, e -> mapper.map(e, Report.class), "reports");
    }

    @Override
    public Mono<Report> findReport() {
        log.info("Fetching report with PK={}", REPORT_PK);
        return getById(REPORT_PK)
                .doOnNext(r -> log.info("Fetched report: {}", r));
    }

    @Override
    public Mono<Report> saveReport(Report report) {
        log.info("saveReport called with parameter: {}", report);
        report.setPartitionKey(REPORT_PK);
        return save(report)
                .doOnNext(r -> log.info("Saved report: {}", r));
    }
}
