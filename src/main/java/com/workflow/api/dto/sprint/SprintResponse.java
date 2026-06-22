package com.workflow.api.dto.sprint;

import com.workflow.api.enums.SprintStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SprintResponse(
        Long id,
        String name,
        String description,
        SprintStatus status,
        LocalDate startDate,
        LocalDate endDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
