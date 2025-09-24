package com.powerup.api.report;

import com.powerup.api.config.ReportPath;
import com.powerup.api.openapi.ReportOpenApi;
import lombok.RequiredArgsConstructor;
import org.springdoc.webflux.core.fn.SpringdocRouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
@RequiredArgsConstructor
public class ReportRouterRest {
    private final ReportPath reportPath;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(ReportHandler handler) {
        return SpringdocRouteBuilder.route()
                .GET(reportPath.getReports(), handler::getReports, ReportOpenApi::getReports)
                .build();
    }
}
