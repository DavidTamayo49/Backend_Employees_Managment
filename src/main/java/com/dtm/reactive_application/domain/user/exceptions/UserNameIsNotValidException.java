package com.dtm.reactive_application.domain.user.exceptions;

import com.dtm.reactive_application.crosscutting.exception.RuleReactiveException;

public final class UserNameIsNotValidException extends RuleReactiveException {
    private static final long serialVersionUID = 1L;

    private UserNameIsNotValidException(final String userMessage) {
        super(userMessage, userMessage, new Exception());
    }

    public static UserNameIsNotValidException create() {
        return new UserNameIsNotValidException("El nombre del empleado es obligatorio y debe tener máximo 100 caracteres");
    }
}
