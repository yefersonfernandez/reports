package com.powerup.model.approvedloan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApprovedLoan {
    private String partitionKey;
    private String loanId;
    private BigDecimal amount;
    private Instant approvedAt;
}