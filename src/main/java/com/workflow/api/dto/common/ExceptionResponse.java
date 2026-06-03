package com.workflow.api.dto.common;

public record ExceptionResponse(
        int status,
        String error,
        String message
) {
}
