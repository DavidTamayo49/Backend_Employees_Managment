package com.dtm.reactive_application.domain.user.exceptions;

import com.dtm.reactive_application.crosscutting.exception.RuleReactiveException;

public final class DocumentTypeDoesNotExistsException extends RuleReactiveException {
    private static final long serialVersionUID = 1L;

    private DocumentTypeDoesNotExistsException(final String userMessage) {
        super(userMessage, userMessage, new Exception());
    }

    public static DocumentTypeDoesNotExistsException create() {
        return new DocumentTypeDoesNotExistsException("El tipo de documento indicado no existe");
    }
}
