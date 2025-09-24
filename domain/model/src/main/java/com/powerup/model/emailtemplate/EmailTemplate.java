package com.powerup.model.emailtemplate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmailTemplate {
    private String templateId;
    private String s3Key;
    private String subject;
}
