package com.powerup.usecase.utils;

import com.powerup.model.report.Report;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class ReportUtils {

    public static Report buildEmptyReport() {
        return Report.builder()
                .totalLoansApproved(0L)
                .totalAmountApproved(BigDecimal.ZERO)
                .build();
    }

    public static Report buildIncrementedReport(Report currentReport, BigDecimal amountToAdd) {
        return Report.builder()
                .totalLoansApproved(currentReport.getTotalLoansApproved() + 1)
                .totalAmountApproved(currentReport.getTotalAmountApproved().add(amountToAdd))
                .build();
    }
}