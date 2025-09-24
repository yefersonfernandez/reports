package com.powerup.model.emailtemplate.gateways;

import com.powerup.model.emailtemplate.EmailTemplate;
import reactor.core.publisher.Mono;

public interface IEmailTemplateRepositoryPort {
    Mono<EmailTemplate> getEmailTemplate(String templateId);
}
