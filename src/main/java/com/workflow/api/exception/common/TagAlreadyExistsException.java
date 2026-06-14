package com.workflow.api.exception.common;

import com.workflow.api.exception.WorkFlowException;

public class TagAlreadyExistsException extends WorkFlowException {
    public TagAlreadyExistsException() {
        super("Já existe uma tag com esse nome");
    }
}
