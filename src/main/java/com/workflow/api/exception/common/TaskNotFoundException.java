package com.workflow.api.exception.common;

import com.workflow.api.exception.WorkFlowException;

public class TaskNotFoundException extends WorkFlowException {
  public TaskNotFoundException() {
    super("Tarefa informada não encontrada");
  }
}
