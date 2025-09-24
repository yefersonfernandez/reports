package com.powerup.model.s3.gateways;

import reactor.core.publisher.Mono;

public interface IS3TemplatePort {
    Mono<String> getTemplateHtml(String key);
}
