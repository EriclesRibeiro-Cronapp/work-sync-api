package com.workflow.api.dto.task;

import com.workflow.api.dto.tag.TagResponse;
import com.workflow.api.dto.user.UserSummaryResponse;
import com.workflow.api.enums.Priority;
import com.workflow.api.enums.TaskStatus;
import com.workflow.api.enums.TaskType;

import java.time.LocalDateTime;
import java.util.Set;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        Priority priority,
        TaskType type,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Set<TagResponse> tags,
        UserSummaryResponse assignedUser
) {
}
