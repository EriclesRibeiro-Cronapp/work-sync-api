package com.workflow.api.dto.task;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskRequest(
        @NotBlank(message = "O campo 'título' é obrigatório")
        String title,
        @NotBlank(message = "(O campo 'descrição' é obrigatório")
        String description
) {
}
