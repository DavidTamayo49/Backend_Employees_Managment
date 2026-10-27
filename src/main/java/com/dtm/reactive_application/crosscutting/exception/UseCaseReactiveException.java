package com.dtm.reactive_application.crosscutting.exception;

import com.dtm.reactive_application.crosscutting.exception.enums.Layer;

public class UseCaseReactiveException extends ReactiveApplicationException {
    private static final long serialVersionUID = 1L;

    public UseCaseReactiveException(final String userMessage, final String technicalMessage, final Exception rootException) {
        super(userMessage, technicalMessage, rootException, Layer.USECASE);
    }

    public static UseCaseReactiveException create(final String userMessage, final String technicalMessage, final Exception rootException) {
        return new UseCaseReactiveException(userMessage, technicalMessage, rootException);
    }

    public static UseCaseReactiveException create(final String userMessage, final String technicalMessage) {
        return new UseCaseReactiveException(userMessage, technicalMessage, new Exception());
    }

    public static UseCaseReactiveException create(final String userMessage) {
        return new UseCaseReactiveException(userMessage, userMessage, new Exception());
    }
}
