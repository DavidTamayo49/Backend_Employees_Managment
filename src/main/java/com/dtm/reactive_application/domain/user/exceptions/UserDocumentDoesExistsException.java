package com.dtm.reactive_application.domain.user.exceptions;

import com.dtm.reactive_application.crosscutting.exception.RuleReactiveException;

public final class UserDocumentDoesExistsException extends RuleReactiveException {
    private static final long serialVersionUID = 1L;

    private UserDocumentDoesExistsException(final String userMessage) {
        super(userMessage, userMessage, new Exception());
    }

    public static UserDocumentDoesExistsException create() {
        return new UserDocumentDoesExistsException("Ya existe un empleado registrado con ese tipo y número de documento");
    }
}
