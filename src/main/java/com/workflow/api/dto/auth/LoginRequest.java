package com.workflow.api.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @Email(message = "Informe um email válido")
        @NotBlank(message = "O campo 'email' é obrigatório")
        String email,

        @NotBlank(message = "O campo 'password' é obrigatório")
        @Size(min = 8, message = "O campo 'password' precisa ter no mínimo 8 caracteres")
        String password
) {
}
