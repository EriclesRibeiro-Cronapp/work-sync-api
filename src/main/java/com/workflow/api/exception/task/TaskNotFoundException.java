package com.workflow.api.exception.task;

import com.workflow.api.exception.WorkFlowException;

public class TaskNotFoundException extends WorkFlowException {
  public TaskNotFoundException() {
    super("Tarefa informada não encontrada");
  }
}
