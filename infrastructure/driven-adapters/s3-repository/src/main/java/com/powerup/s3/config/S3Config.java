package com.powerup.s3.config;

import com.powerup.s3.config.model.S3ConnectionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.metrics.MetricPublisher;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;

@Configuration
public class S3Config {

    @Bean
    public S3AsyncClient s3AsyncClient(S3ConnectionProperties s3Properties, MetricPublisher publisher) {
        return S3AsyncClient.builder()
                .overrideConfiguration(o -> o.addMetricPublisher(publisher))
                .region(Region.of(s3Properties.region()))
                .build();
    }
}
