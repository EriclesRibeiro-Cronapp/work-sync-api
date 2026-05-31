package com.workflow.api.exception.auth;

import com.workflow.api.exception.WorkFlowException;

public class EmailAlreadyExistsException extends WorkFlowException {

    public EmailAlreadyExistsException() {
        super("E-mail já cadastrado");
    }
}
