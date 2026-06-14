package com.workflow.api.dto.task;

import com.workflow.api.entity.Tag;
import com.workflow.api.enums.Priority;
import com.workflow.api.enums.TaskStatus;
import com.workflow.api.enums.TaskType;

import java.time.LocalDateTime;
import java.util.Set;

public record TaskResponse(
        String title,
        String description,
        TaskStatus status,
        Priority priority,
        TaskType type,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<Tag> tags
) {
}
