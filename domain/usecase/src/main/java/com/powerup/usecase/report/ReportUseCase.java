package com.powerup.usecase.report;

import com.powerup.model.approvedloan.ApprovedLoan;
import com.powerup.model.approvedloan.gateways.IApprovedLoanRepositoryPort;
import com.powerup.model.emailtemplate.gateways.IEmailTemplateRepositoryPort;
import com.powerup.model.report.EmailReport;
import com.powerup.model.report.Report;
import com.powerup.model.report.gateways.IEmailSenderPort;
import com.powerup.model.report.gateways.IReportRepositoryPort;
import com.powerup.model.s3.gateways.IS3TemplatePort;
import com.powerup.usecase.utils.ReportUtils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.powerup.usecase.utils.ReportUtils.buildEmailReport;
import static com.powerup.usecase.utils.ReportUtils.buildEmptyReport;
import static com.powerup.usecase.utils.ReportUtils.buildIncrementedReport;

@RequiredArgsConstructor
public class ReportUseCase {

    private final IReportRepositoryPort reportRepositoryPort;
    private final IApprovedLoanRepositoryPort approvedLoanRepositoryPort;
    private final IEmailSenderPort emailSenderPort;
    private final IEmailTemplateRepositoryPort emailTemplateRepositoryPort;
    private final IS3TemplatePort s3TemplatePort;

    public Mono<Report> getReport() {
        return reportRepositoryPort.findReport();
    }

    public Mono<Void> updateReportOnLoanApproved(ApprovedLoan loan) {
        return approvedLoanRepositoryPort.existsByLoanId(loan.getLoanId())
                .filter(exists -> !exists)
                .flatMap(ignore -> approvedLoanRepositoryPort.saveApprovedLoan(loan)
                        .then(reportRepositoryPort.findReport()
                                .defaultIfEmpty(buildEmptyReport())
                                .map(report -> buildIncrementedReport(report, loan.getAmount()))
                                .flatMap(reportRepositoryPort::saveReport)
                        )
                )
                .then();
    }

    public Mono<Void> sendReportEmail(String templateId) {
        return reportRepositoryPort.findReport()
                .defaultIfEmpty(buildEmptyReport())
                .flatMap(report -> getEmailTemplate(report, templateId))
                .flatMap(emailSenderPort::sendEmail)
                .log()
                .then();
    }

    private Mono<EmailReport> getEmailTemplate(Report report, String templateId) {
        return emailTemplateRepositoryPort.getEmailTemplate(templateId)
                .flatMap(template -> s3TemplatePort.getTemplateHtml(template.getS3Key())
                        .map(html -> buildEmailReport(report, template.getSubject(), html))
                );
    }
}
