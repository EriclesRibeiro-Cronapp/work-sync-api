package com.workflow.api.dto.tag;

import jakarta.validation.constraints.NotBlank;

public record CreateTagRequest(
        @NotBlank(message = "O campo 'name' é obrigatório")
        String name
) {
}
