package com.dtm.reactive_application.crosscutting.exception;

import com.dtm.reactive_application.crosscutting.exception.enums.Layer;

public class DomainReactiveException extends ReactiveApplicationException {
    private static final long serialVersionUID = 1L;

    public DomainReactiveException(final String userMessage, final String technicalMessage, final Exception rootException) {
        super(userMessage, technicalMessage, rootException, Layer.DOMAIN);
    }

    public static DomainReactiveException create(final String userMessage, final String technicalMessage, final Exception rootException) {
        return new DomainReactiveException(userMessage, technicalMessage, rootException);
    }

    public static DomainReactiveException create(final String userMessage, final String technicalMessage) {
        return new DomainReactiveException(userMessage, technicalMessage, new Exception());
    }

    public static DomainReactiveException create(final String userMessage) {
        return new DomainReactiveException(userMessage, userMessage, new Exception());
    }
}
