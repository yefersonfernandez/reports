package com.powerup.emailses.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailLogConstants {
    public static final String SENDING_EMAIL = "Sending SES email to {} with report {}";
    public static final String EMAIL_SENT_SUCCESS = "SES email successfully sent to {}";
    public static final String ERROR_SENDING_EMAIL = "Error sending SES email: {}";
}
