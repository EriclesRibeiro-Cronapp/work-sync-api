package com.workflow.api.dto.tag;

import jakarta.validation.constraints.NotBlank;

public record UpdateTagRequest(
        @NotBlank(message = "O campo 'nome' é obrigatório")
        String name
) {
}