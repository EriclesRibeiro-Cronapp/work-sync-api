package com.workflow.api.dto;

public record CreateTaskRequest(
    String title,
    String description
) {
}
