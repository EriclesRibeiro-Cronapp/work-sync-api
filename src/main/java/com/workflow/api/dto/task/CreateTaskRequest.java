package com.workflow.api.dto.task;

import com.workflow.api.enums.Priority;
import com.workflow.api.enums.TaskType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTaskRequest(
        @NotBlank(message = "O campo 'title' é obrigatório")
        String title,
        String description,
        @NotNull(message = "O campo 'priority' é obrigatório")
        Priority priority,
        @NotNull(message = "O campo 'type' é obrigatório")
        TaskType type
) {
}
