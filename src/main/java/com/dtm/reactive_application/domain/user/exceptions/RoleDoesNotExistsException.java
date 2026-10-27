package com.dtm.reactive_application.domain.user.exceptions;

import com.dtm.reactive_application.crosscutting.exception.RuleReactiveException;

public final class RoleDoesNotExistsException extends RuleReactiveException {
    private static final long serialVersionUID = 1L;

    private RoleDoesNotExistsException(final String userMessage) {
        super(userMessage, userMessage, new Exception());
    }

    public static RoleDoesNotExistsException create() {
        return new RoleDoesNotExistsException("El rol indicado no existe");
    }
}
