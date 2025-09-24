package com.powerup.api.dto.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiResponse(
        responseCode = "401",
        description = "Unauthorized - Authentication is required or failed",
        content = @Content(schema = @Schema(implementation = CustomError.class))
)
@ApiResponse(
        responseCode = "403",
        description = "Forbidden - You do not have permission to access this resource",
        content = @Content(schema = @Schema(implementation = CustomError.class))
)
public class CustomError {

    private Integer statusCode;
    private String error;
    private String message;
    private String path;
    private List<String> errors;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
}
