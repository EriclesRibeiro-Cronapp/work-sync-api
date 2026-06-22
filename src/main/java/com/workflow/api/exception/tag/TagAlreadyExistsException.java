package com.workflow.api.exception.tag;

import com.workflow.api.exception.WorkFlowException;

public class TagAlreadyExistsException extends WorkFlowException {
    public TagAlreadyExistsException() {
        super("Já existe uma tag com esse nome");
    }
}
