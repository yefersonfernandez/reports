package com.powerup.model.report.gateways;

import com.powerup.model.report.EmailReport;
import reactor.core.publisher.Mono;

public interface IEmailSenderPort {
    Mono<Void> sendEmail(EmailReport report);
}
