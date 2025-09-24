package com.powerup.dynamodb.adapters.approvedloan;

import com.powerup.dynamodb.entity.ApprovedLoanEntity;
import com.powerup.model.approvedloan.ApprovedLoan;
import com.powerup.model.approvedloan.gateways.IApprovedLoanRepositoryPort;
import com.powerup.dynamodb.helper.TemplateAdapterOperations;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;

@Slf4j
@Repository
public class ApprovedLoanRepositoryAdapter
        extends TemplateAdapterOperations<ApprovedLoan, String, ApprovedLoanEntity>
        implements IApprovedLoanRepositoryPort {

    private static final String APPROVED_LOANS_PK = "APPROVED-LOANS";

    public ApprovedLoanRepositoryAdapter(DynamoDbEnhancedAsyncClient client, ObjectMapper mapper) {
        super(client, mapper, e -> mapper.map(e, ApprovedLoan.class), "approved_loans");
    }

    @Override
    public Mono<Boolean> existsByLoanId(String loanId) {
        log.debug("existsByLoanId called with parameter: loanId={}", loanId);
        return getById(APPROVED_LOANS_PK, loanId)
                .hasElement()
                .doOnNext(exists -> log.debug("Loan exists? {}", exists));
    }

    @Override
    public Mono<ApprovedLoan> saveApprovedLoan(ApprovedLoan loan) {
        log.info("saveApprovedLoan called with parameter: {}", loan);
        loan.setPartitionKey(APPROVED_LOANS_PK);
        return save(loan)
                .doOnNext(l -> log.info("Saved loan: {}", l));
    }
}
