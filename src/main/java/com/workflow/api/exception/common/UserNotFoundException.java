package com.workflow.api.exception.common;

import com.workflow.api.exception.WorkFlowException;

public class UserNotFoundException extends WorkFlowException {
    public UserNotFoundException() {
        super("Usuário ou email informado não encontrado");
    }
}
