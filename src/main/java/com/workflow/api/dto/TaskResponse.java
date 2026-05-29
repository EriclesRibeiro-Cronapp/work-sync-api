package com.workflow.api.dto;

public record TaskResponse(
        String title,
        String description,
        boolean completed
) {
}
