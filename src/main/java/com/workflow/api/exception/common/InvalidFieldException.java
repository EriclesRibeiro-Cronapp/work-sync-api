package com.workflow.api.exception.common;

import com.workflow.api.exception.WorkFlowException;

public class InvalidFieldException extends WorkFlowException {
    public InvalidFieldException(String message) {
        super(message);
    }
}
