package com.workflow.api.dto;

public record ExceptionResponse(
        int status,
        String error,
        String message
) {
}
