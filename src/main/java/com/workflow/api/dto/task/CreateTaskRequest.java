package com.workflow.api.dto.task;

public record CreateTaskRequest(
    String title,
    String description
) {
}
