package com.workflow.api.dto;

public record RegisterRequest(
        String name,
        String email,
        String password
) {
}
