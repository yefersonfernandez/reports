package com.powerup.dynamodb.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;
import java.math.BigDecimal;
import java.time.Instant;

@DynamoDbBean
public class ApprovedLoanEntity {

    private String partitionKey;
    private String loanId;
    private BigDecimal amount;
    private Instant approvedAt;

    @DynamoDbPartitionKey()
    @DynamoDbAttribute("APPROVED-LOANS")
    public String getPartitionKey() { return partitionKey; }
    public void setPartitionKey(String partitionKey) { this.partitionKey = partitionKey; }

    @DynamoDbSortKey
    @DynamoDbAttribute("loanId")
    public String getLoanId() { return loanId; }
    public void setLoanId(String loanId) { this.loanId = loanId; }

    @DynamoDbAttribute("amount")
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    @DynamoDbAttribute("approvedAt")
    public Instant getApprovedAt() { return approvedAt; }
    public void setApprovedAt(Instant approvedAt) { this.approvedAt = approvedAt; }
}
