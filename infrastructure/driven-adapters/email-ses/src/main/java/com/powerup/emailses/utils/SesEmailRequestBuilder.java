package com.powerup.emailses.utils;

import com.powerup.emailses.config.SesProperties;
import com.powerup.model.report.EmailReport;
import lombok.experimental.UtilityClass;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;

import static com.powerup.emailses.constants.EmailConstants.BODY_TEMPLATE_HTML;
import static com.powerup.emailses.constants.EmailConstants.SUBJECT;

@UtilityClass
public class SesEmailRequestBuilder {

    public static SendEmailRequest buildSendEmailRequest(SesProperties sesProperties, EmailReport report) {
        return SendEmailRequest.builder()
                .source(sesProperties.sourceEmail())
                .destination(destination -> destination.toAddresses(sesProperties.destinationEmail()))
                .message(msg -> msg.subject(sub -> sub.data(SUBJECT))
                        .body(body -> body.html(html -> html.data(buildReportBody(report))))
                )
                .build();
    }

    private static String buildReportBody(EmailReport report) {
        return String.format(
                BODY_TEMPLATE_HTML,
                report.getTotalLoansApproved(),
                report.getTotalAmountApproved()
        );
    }
}