package com.dtm.reactive_application.crosscutting.exception;

import com.dtm.reactive_application.crosscutting.exception.enums.Layer;

public class RepositoryReactiveException extends ReactiveApplicationException {
    private static final long serialVersionUID = 1L;

    public RepositoryReactiveException(final String userMessage, final String technicalMessage, final Exception rootException) {
        super(userMessage, technicalMessage, rootException, Layer.REPOSITORY);
    }

    public static RepositoryReactiveException create(final String userMessage, final String technicalMessage, final Exception rootException) {
        return new RepositoryReactiveException(userMessage, technicalMessage, rootException);
    }

    public static RepositoryReactiveException create(final String userMessage, final String technicalMessage) {
        return new RepositoryReactiveException(userMessage, technicalMessage, new Exception());
    }

    public static RepositoryReactiveException create(final String userMessage) {
        return new RepositoryReactiveException(userMessage, userMessage, new Exception());
    }
}
