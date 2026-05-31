package com.workflow.api.exception.auth;

import com.workflow.api.exception.WorkFlowException;

public class InvalidCredentialsException extends WorkFlowException {

    public InvalidCredentialsException() {
        super("Usuário inexistente ou senha inválida");
    }
}
