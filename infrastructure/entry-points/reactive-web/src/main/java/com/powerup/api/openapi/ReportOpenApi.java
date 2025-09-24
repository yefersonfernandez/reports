package com.powerup.api.openapi;

import com.powerup.api.dto.error.CustomError;
import com.powerup.api.dto.response.ReportResponseDto;
import lombok.experimental.UtilityClass;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.content.Builder.contentBuilder;
import static org.springdoc.core.fn.builders.schema.Builder.schemaBuilder;
import static org.springdoc.core.fn.builders.securityrequirement.Builder.securityRequirementBuilder;

@UtilityClass
public class ReportOpenApi {

    private final String TAG = "Report";

    private final String SUCCESS_CODE = String.valueOf(HttpStatus.OK.value());
    private final String UNAUTHORIZED_CODE = String.valueOf(HttpStatus.UNAUTHORIZED.value());
    private final String FORBIDDEN_CODE = String.valueOf(HttpStatus.FORBIDDEN.value());

    private final String UNAUTHORIZED = HttpStatus.UNAUTHORIZED.getReasonPhrase();
    private final String FORBIDDEN = HttpStatus.FORBIDDEN.getReasonPhrase();

    public Builder getReports(Builder builder) {
        return builder
                .operationId("getReports")
                .description("Retrieves the report of approved loans with total count and total amount")
                .tag(TAG)
                .security(securityRequirementBuilder().name("bearerAuth"))
                .response(responseBuilder()
                        .responseCode(SUCCESS_CODE)
                        .description("Report retrieved successfully")
                        .content(contentBuilder()
                                .mediaType(MediaType.APPLICATION_JSON_VALUE)
                                .schema(schemaBuilder().implementation(ReportResponseDto.class))))
                .response(responseBuilder()
                        .responseCode(UNAUTHORIZED_CODE)
                        .description(UNAUTHORIZED)
                        .content(contentBuilder()
                                .mediaType(MediaType.APPLICATION_JSON_VALUE)
                                .schema(schemaBuilder().implementation(CustomError.class))))
                .response(responseBuilder()
                        .responseCode(FORBIDDEN_CODE)
                        .description(FORBIDDEN)
                        .content(contentBuilder()
                                .mediaType(MediaType.APPLICATION_JSON_VALUE)
                                .schema(schemaBuilder().implementation(CustomError.class))));
    }
}
