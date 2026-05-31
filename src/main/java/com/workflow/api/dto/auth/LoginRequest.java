package com.workflow.api.dto.auth;

public record LoginRequest(
        String email,
        String password
) {
}
