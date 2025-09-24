package com.powerup.emailses.utils;

import com.powerup.emailses.config.SesProperties;
import com.powerup.model.report.EmailReport;
import lombok.experimental.UtilityClass;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;

@UtilityClass
public class SesEmailRequestBuilder {

    public static SendEmailRequest buildSendEmailRequest(SesProperties sesProperties, EmailReport report) {
        return SendEmailRequest.builder()
                .source(sesProperties.sourceEmail())
                .destination(destination -> destination.toAddresses(sesProperties.destinationEmail()))
                .message(msg -> msg.subject(sub -> sub.data(report.getSubject()))
                        .body(body -> body.html(html -> html.data(buildReportBody(report))))
                )
                .build();
    }

    private static String buildReportBody(EmailReport report) {
        return report.getBodyHtml()
                .replace("${totalLoansApproved}", String.valueOf(report.getTotalLoansApproved()))
                .replace("${totalAmountApproved}", String.valueOf(report.getTotalAmountApproved()));
    }
}