package com.powerup.sqs.listener.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SQSLogConstants {
    public static final String RECEIVED_MESSAGE = "Received SQS message in reportTaskQueue: {}";
    public static final String ERROR_SENDING_EMAIL = "Error sending daily report email: {}";
}
