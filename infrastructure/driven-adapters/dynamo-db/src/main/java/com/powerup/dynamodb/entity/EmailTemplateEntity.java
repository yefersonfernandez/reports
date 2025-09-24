package com.powerup.dynamodb.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;

@DynamoDbBean
public class EmailTemplateEntity {

    private String templateId;
    private String s3Key;
    private String subject;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("templateId")
    public String getTemplateId() { return templateId; }
    public void setTemplateId(String templateId) { this.templateId = templateId; }

    @DynamoDbAttribute("s3Key")
    public String getS3Key() { return s3Key; }
    public void setS3Key(String s3Key) { this.s3Key = s3Key; }

    @DynamoDbAttribute("subject")
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
}
