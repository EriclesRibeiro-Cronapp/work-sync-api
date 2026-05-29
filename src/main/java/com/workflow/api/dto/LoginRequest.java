package com.workflow.api.dto;

public record LoginRequest(
        String email,
        String password
) {
}
