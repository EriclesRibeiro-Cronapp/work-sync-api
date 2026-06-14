package com.workflow.api.exception.common;

import com.workflow.api.exception.WorkFlowException;

public class TagNotFoundException extends WorkFlowException {
  public TagNotFoundException() {
    super("Tag informada não encontrada");
  }
}
