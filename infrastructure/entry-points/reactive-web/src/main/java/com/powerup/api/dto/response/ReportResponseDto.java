package com.powerup.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Response DTO for a report with total approved loans and total amount approved")
public record ReportResponseDto(

        @Schema(description = "Total number of approved loans", example = "10")
        Long totalLoansApproved,

        @Schema(description = "Total amount of approved loans", example = "15000.00")
        BigDecimal totalAmountApproved

) {}
