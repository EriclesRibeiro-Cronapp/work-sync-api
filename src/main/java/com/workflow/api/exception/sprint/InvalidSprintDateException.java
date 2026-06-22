package com.workflow.api.exception.sprint;

import com.workflow.api.exception.WorkFlowException;

public class InvalidSprintDateException extends WorkFlowException {
  public InvalidSprintDateException() {
    super("A data de início não pode ser maior que a data de fim da sprint");
  }
}
