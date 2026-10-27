package com.dtm.reactive_application.crosscutting.exception;

import com.dtm.reactive_application.crosscutting.exception.enums.Layer;

public class InfrastructureReactiveException extends ReactiveApplicationException {
    private static final long serialVersionUID = 1L;

    public InfrastructureReactiveException(final String userMessage, final String technicalMessage, final Exception rootException) {
        super(userMessage, technicalMessage, rootException, Layer.INFRASTRUCTURE);
    }

    public static InfrastructureReactiveException create(final String userMessage, final String technicalMessage, final Exception rootException) {
        return new InfrastructureReactiveException(userMessage, technicalMessage, rootException);
    }

    public static InfrastructureReactiveException create(final String userMessage, final String technicalMessage) {
        return new InfrastructureReactiveException(userMessage, technicalMessage, new Exception());
    }

    public static InfrastructureReactiveException create(final String userMessage) {
        return new InfrastructureReactiveException(userMessage, userMessage, new Exception());
    }
}
