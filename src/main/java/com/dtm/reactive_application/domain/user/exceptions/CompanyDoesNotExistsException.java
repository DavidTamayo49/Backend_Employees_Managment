package com.dtm.reactive_application.domain.user.exceptions;

import com.dtm.reactive_application.crosscutting.exception.RuleReactiveException;

public final class CompanyDoesNotExistsException extends RuleReactiveException {
    private static final long serialVersionUID = 1L;

    private CompanyDoesNotExistsException(final String userMessage) {
        super(userMessage, userMessage, new Exception());
    }

    public static CompanyDoesNotExistsException create() {
        return new CompanyDoesNotExistsException("La empresa indicada no existe");
    }
}
