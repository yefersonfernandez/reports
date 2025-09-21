package com.powerup.emailses.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailConstants {
    public static final String SUBJECT = "Reporte de Préstamos Aprobados";
    public static final String BODY_TEMPLATE_HTML = """
        <p><strong>Reporte de préstamos aprobados</strong></p>
        <p>Número total de préstamos aprobados: %d<br/>
        Monto total aprobado: %s</p>
        """;
}
