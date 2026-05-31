package com.workflow.api.dto.task;

public record TaskResponse(
        String title,
        String description,
        boolean completed
) {
}
