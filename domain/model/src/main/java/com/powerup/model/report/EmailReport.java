package com.powerup.model.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailReport {
    private Long totalLoansApproved;
    private BigDecimal totalAmountApproved;
    private String subject;
    private String bodyHtml;
}
