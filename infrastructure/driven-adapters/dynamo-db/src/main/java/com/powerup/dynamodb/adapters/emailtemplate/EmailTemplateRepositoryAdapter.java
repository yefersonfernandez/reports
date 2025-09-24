package com.powerup.dynamodb.adapters.emailtemplate;

import com.powerup.dynamodb.entity.EmailTemplateEntity;
import com.powerup.dynamodb.helper.TemplateAdapterOperations;
import com.powerup.model.emailtemplate.EmailTemplate;
import com.powerup.model.emailtemplate.gateways.IEmailTemplateRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;

@Slf4j
@Repository
public class EmailTemplateRepositoryAdapter
        extends TemplateAdapterOperations<EmailTemplate, String, EmailTemplateEntity>
        implements IEmailTemplateRepositoryPort {

    public EmailTemplateRepositoryAdapter(
            DynamoDbEnhancedAsyncClient client,
            ObjectMapper mapper,
            @Value("${aws.dynamodb.emailTemplates.tableName}") String tableName) {
        super(client, mapper, e -> mapper.map(e, EmailTemplate.class), tableName);
    }

    @Override
    public Mono<EmailTemplate> getEmailTemplate(String templateId) {
        log.info("Fetching email template with templateId={}", templateId);
        return getById(templateId)
                .doOnNext(t -> log.info("Fetched email template: {}", t));
    }
}
