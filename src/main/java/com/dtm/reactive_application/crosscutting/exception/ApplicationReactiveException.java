package com.dtm.reactive_application.crosscutting.exception;

import com.dtm.reactive_application.crosscutting.exception.enums.Layer;

public class ApplicationReactiveException extends ReactiveApplicationException {
    private static final long serialVersionUID = 1L;

    public ApplicationReactiveException(final String userMessage, final String technicalMessage, final Exception rootException) {
        super(userMessage, technicalMessage, rootException, Layer.APPLICATION);
    }

    public static ApplicationReactiveException create(final String userMessage, final String technicalMessage, final Exception rootException) {
        return new ApplicationReactiveException(userMessage, technicalMessage, rootException);
    }

    public static ApplicationReactiveException create(final String userMessage, final String technicalMessage) {
        return new ApplicationReactiveException(userMessage, technicalMessage, new Exception());
    }

    public static ApplicationReactiveException create(final String userMessage) {
        return new ApplicationReactiveException(userMessage, userMessage, new Exception());
    }
}
