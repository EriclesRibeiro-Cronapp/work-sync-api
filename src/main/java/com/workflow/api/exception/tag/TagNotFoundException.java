package com.workflow.api.exception.tag;

import com.workflow.api.exception.WorkFlowException;

public class TagNotFoundException extends WorkFlowException {
  public TagNotFoundException() {
    super("Tag informada não encontrada");
  }
}
