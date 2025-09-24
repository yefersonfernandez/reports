package com.powerup.dynamodb.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;
import java.math.BigDecimal;

@DynamoDbBean
public class ReportEntity {

    private String partitionKey;
    private Long totalLoansApproved;
    private BigDecimal totalAmountApproved;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("REPORT")
    public String getPartitionKey() { return partitionKey; }
    public void setPartitionKey(String partitionKey) { this.partitionKey = partitionKey; }

    @DynamoDbAttribute("totalLoansApproved")
    public Long getTotalLoansApproved() { return totalLoansApproved; }
    public void setTotalLoansApproved(Long totalLoansApproved) { this.totalLoansApproved = totalLoansApproved; }

    @DynamoDbAttribute("totalAmountApproved")
    public BigDecimal getTotalAmountApproved() { return totalAmountApproved; }
    public void setTotalAmountApproved(BigDecimal totalAmountApproved) { this.totalAmountApproved = totalAmountApproved; }
}