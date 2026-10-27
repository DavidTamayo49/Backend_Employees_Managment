package com.dtm.reactive_application.domain.user.exceptions;

import com.dtm.reactive_application.crosscutting.exception.RuleReactiveException;

public final class UserDoesNotExistsException extends RuleReactiveException {
    private static final long serialVersionUID = 1L;

    private UserDoesNotExistsException(final String userMessage) {
        super(userMessage, userMessage, new Exception());
    }

    public static UserDoesNotExistsException create() {
        return new UserDoesNotExistsException("El empleado indicado no existe");
    }
}
