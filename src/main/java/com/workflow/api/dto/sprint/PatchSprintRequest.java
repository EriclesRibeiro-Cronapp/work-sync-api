package com.workflow.api.dto.sprint;

import com.workflow.api.enums.SprintStatus;

import java.time.LocalDate;

public record PatchSprintRequest(
        String name,
        String description,
        SprintStatus status,
        LocalDate startDate,
        LocalDate endDate
) {
}
