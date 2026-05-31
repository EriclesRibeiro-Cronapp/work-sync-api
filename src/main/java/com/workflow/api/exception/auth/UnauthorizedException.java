package com.workflow.api.exception.auth;

import com.workflow.api.exception.WorkFlowException;

public class UnauthorizedException extends WorkFlowException {

    public UnauthorizedException() {
        super("Não autorizado");
    }
}
