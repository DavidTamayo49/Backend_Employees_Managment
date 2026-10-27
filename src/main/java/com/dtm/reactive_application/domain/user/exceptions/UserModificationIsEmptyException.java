package com.dtm.reactive_application.domain.user.exceptions;

import com.dtm.reactive_application.crosscutting.exception.RuleReactiveException;

public final class UserModificationIsEmptyException extends RuleReactiveException {
    private static final long serialVersionUID = 1L;

    private UserModificationIsEmptyException(final String userMessage) {
        super(userMessage, userMessage, new Exception());
    }

    public static UserModificationIsEmptyException create() {
        return new UserModificationIsEmptyException("Debe enviar al menos un campo para modificar");
    }
}
