package com.workflow.api.dto.sprint;

import com.workflow.api.dto.task.TaskResponse;
import com.workflow.api.enums.SprintStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record SprintDetailsResponse(
        Long id,
        String name,
        String description,
        SprintStatus status,
        LocalDate startDate,
        LocalDate endDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<TaskResponse> tasks
) {
}
