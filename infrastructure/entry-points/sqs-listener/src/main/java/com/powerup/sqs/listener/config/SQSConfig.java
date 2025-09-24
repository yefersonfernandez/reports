package com.powerup.sqs.listener.config;

import com.powerup.sqs.listener.helper.SQSListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.Message;
import java.util.function.Function;

@Configuration
public class SQSConfig {

    @Bean
    public SqsAsyncClient configSqs(SQSProperties properties) {
        return SqsAsyncClient.builder()
                .region(Region.of(properties.region()))
                .credentialsProvider(DefaultCredentialsProvider.builder().build())
                .build();
    }

    @Bean
    public SQSListener approvedLoanQueueListener(
            SqsAsyncClient client,
            SQSProperties properties,
            @Qualifier("approvedLoanQueueProcessor")
            Function<Message, Mono<Void>> fn
    ) {
        return createListener(client, properties, properties.approvedLoanQueueUrl(), fn);
    }

    @Bean
    public SQSListener reportTaskQueueListener(
            SqsAsyncClient client,
            SQSProperties properties,
            @Qualifier("reportTaskQueueProcessor")
            Function<Message, Mono<Void>> fn
    ) {
        return createListener(client, properties, properties.reportTaskQueueUrl(), fn);
    }

    private SQSListener createListener(
            SqsAsyncClient client,
            SQSProperties properties,
            String queueUrl,
            Function<Message, Mono<Void>> processor
    ) {
        return SQSListener.builder()
                .client(client)
                .properties(properties)
                .queueUrl(queueUrl)
                .processor(processor)
                .build()
                .start();
    }
}
