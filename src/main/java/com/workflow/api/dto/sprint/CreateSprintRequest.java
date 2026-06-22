package com.workflow.api.dto.sprint;

import com.workflow.api.enums.SprintStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateSprintRequest(
        @NotBlank(message = "O campo 'name' é obrigatório")
        String name,
        String description,
        @NotNull(message = "O campo 'status' é obrigatório")
        SprintStatus status,
        @NotNull(message = "O campo 'startDate' é obrigatório")
        LocalDate startDate,
        @NotNull(message = "O campo 'endDate' é obrigatório")
        LocalDate endDate
) {
}
