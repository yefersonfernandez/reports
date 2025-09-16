package com.powerup.sqs.listener.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record LoanApprovedDto(
        String loanId,
        BigDecimal amount,
        Instant approvedAt
) {}
