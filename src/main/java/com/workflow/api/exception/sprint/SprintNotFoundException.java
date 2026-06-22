package com.workflow.api.exception.sprint;

import com.workflow.api.exception.WorkFlowException;

public class SprintNotFoundException extends WorkFlowException {
  public SprintNotFoundException() {
    super("Sprint informada não encontrada");
  }
}
