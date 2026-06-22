package com.workflow.api.dto.task;

import com.workflow.api.enums.Priority;
import com.workflow.api.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UpdateTaskRequest(
        @NotBlank(message = "O campo 'title' é obrigatório")
        String title,
        String description,
        @NotNull(message = "O campo 'status' é obrigatório")
        TaskStatus status,
        @NotNull(message = "O campo 'priority' é obrigatório")
        Priority priority,
        Long assignedId,
        Set<Long> tagIds
) {
}
