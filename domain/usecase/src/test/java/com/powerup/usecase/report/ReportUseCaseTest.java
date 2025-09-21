package com.powerup.usecase.report;

import com.powerup.model.approvedloan.ApprovedLoan;
import com.powerup.model.approvedloan.gateways.IApprovedLoanRepositoryPort;
import com.powerup.model.report.Report;
import com.powerup.model.report.gateways.IEmailSenderPort;
import com.powerup.model.report.gateways.IReportRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportUseCaseTest {

    @Mock
    private IReportRepositoryPort reportRepositoryPort;
    @Mock
    private IApprovedLoanRepositoryPort approvedLoanRepositoryPort;
    @Mock
    private IEmailSenderPort emailSenderPort;

    @InjectMocks
    private ReportUseCase reportUseCase;

    private ApprovedLoan approvedLoan;
    private Report existingReport;

    @BeforeEach
    void setUp() {
        approvedLoan = ApprovedLoan.builder()
                .partitionKey("loan#1")
                .loanId("1")
                .amount(BigDecimal.valueOf(5000))
                .approvedAt(Instant.now())
                .build();

        existingReport = Report.builder()
                .partitionKey("report#1")
                .totalLoansApproved(2L)
                .totalAmountApproved(BigDecimal.valueOf(10000))
                .build();
    }

    @Test
    @DisplayName("Must return report from repository")
    void testGetReport() {
        when(reportRepositoryPort.findReport()).thenReturn(Mono.just(existingReport));

        StepVerifier.create(reportUseCase.getReport())
                .expectNext(existingReport)
                .verifyComplete();
    }

    @Test
    @DisplayName("Must not update report if loan already exists")
    void testUpdateReportOnLoanApprovedAlreadyExists() {
        when(approvedLoanRepositoryPort.existsByLoanId(approvedLoan.getLoanId())).thenReturn(Mono.just(true));

        StepVerifier.create(reportUseCase.updateReportOnLoanApproved(approvedLoan))
                .verifyComplete();

        verify(approvedLoanRepositoryPort, never()).saveApprovedLoan(any());
        verify(reportRepositoryPort, never()).saveReport(any());
    }


    @Test
    @DisplayName("Must update report if loan does not exist and report exists")
    void testUpdateReportOnLoanApprovedWhenReportExists() {
        when(approvedLoanRepositoryPort.existsByLoanId(approvedLoan.getLoanId())).thenReturn(Mono.just(false));
        when(approvedLoanRepositoryPort.saveApprovedLoan(approvedLoan)).thenReturn(Mono.just(approvedLoan));
        when(reportRepositoryPort.findReport()).thenReturn(Mono.just(existingReport));
        when(reportRepositoryPort.saveReport(any(Report.class))).thenReturn(Mono.just(existingReport));

        StepVerifier.create(reportUseCase.updateReportOnLoanApproved(approvedLoan))
                .verifyComplete();
    }

    @Test
    @DisplayName("Must update report if loan does not exist and no report exists")
    void testUpdateReportOnLoanApprovedWhenNoReportExists() {
        when(approvedLoanRepositoryPort.existsByLoanId(approvedLoan.getLoanId())).thenReturn(Mono.just(false));
        when(approvedLoanRepositoryPort.saveApprovedLoan(approvedLoan)).thenReturn(Mono.just(approvedLoan));
        when(reportRepositoryPort.findReport()).thenReturn(Mono.empty());
        when(reportRepositoryPort.saveReport(any(Report.class))).thenReturn(Mono.just(existingReport));

        StepVerifier.create(reportUseCase.updateReportOnLoanApproved(approvedLoan))
                .verifyComplete();
    }

    @Test
    @DisplayName("Must send report email when report exists")
    void testSendReportEmailWhenReportExists() {
        when(reportRepositoryPort.findReport()).thenReturn(Mono.just(existingReport));
        when(emailSenderPort.sendEmail(any())).thenReturn(Mono.empty());

        StepVerifier.create(reportUseCase.sendReportEmail())
                .verifyComplete();

        verify(emailSenderPort).sendEmail(any());
    }

    @Test
    @DisplayName("Must send report email when no report exists (build empty report)")
    void testSendReportEmailWhenNoReportExists() {
        when(reportRepositoryPort.findReport()).thenReturn(Mono.empty());
        when(emailSenderPort.sendEmail(any())).thenReturn(Mono.empty());

        StepVerifier.create(reportUseCase.sendReportEmail())
                .verifyComplete();

        verify(emailSenderPort).sendEmail(any());
    }
}
