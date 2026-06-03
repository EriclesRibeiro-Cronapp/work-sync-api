package com.workflow.api.exception;

public class UsernameNotFoundException extends WorkFlowException {
    public UsernameNotFoundException() {
        super("Usuário ou email informado não encontrado");
    }
}
