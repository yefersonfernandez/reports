package com.powerup.model.approvedloan.gateways;

import com.powerup.model.approvedloan.ApprovedLoan;
import reactor.core.publisher.Mono;

public interface IApprovedLoanRepositoryPort {
    Mono<Boolean> existsByLoanId(String loanId);
    Mono<ApprovedLoan> saveApprovedLoan(ApprovedLoan approvedLoan);
}