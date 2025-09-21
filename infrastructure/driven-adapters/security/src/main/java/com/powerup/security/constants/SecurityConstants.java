package com.powerup.security.constants;

public class SecurityConstants {

    private SecurityConstants() {}

    public static final String PUBLIC_KEY_HEADER = "-----BEGIN PUBLIC KEY-----";
    public static final String PUBLIC_KEY_FOOTER = "-----END PUBLIC KEY-----";
    public static final String ALGORITHM_RSA = "RSA";

    public static final String ROLE_PREFIX = "ROLE_";
    public static final String CLAIM_ROLE = "role";
    public static final String ROLE_ADMIN = "ADMIN";

    public static final String ACTUATOR_HEALTH_URL = "/report/actuator/health";
    public static final String REPORTS_URL = "/report/api/v1/reports";
    public static final String[] PUBLIC_SWAGGER_PATHS = {
            "/report/api/doc/**", "/report/v3/api-docs/**",
            "/report/swagger-ui.html", "/report/swagger-ui/**"
    };
}
