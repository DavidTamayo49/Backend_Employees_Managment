package com.dtm.reactive_application.domain.user.exceptions;

import com.dtm.reactive_application.crosscutting.exception.RuleReactiveException;

public final class UserDocumentIsNotValidException extends RuleReactiveException {
    private static final long serialVersionUID = 1L;

    private UserDocumentIsNotValidException(final String userMessage) {
        super(userMessage, userMessage, new Exception());
    }

    public static UserDocumentIsNotValidException create() {
        return new UserDocumentIsNotValidException("El documento es obligatorio y debe tener entre 5 y 20 caracteres alfanuméricos");
    }
}
