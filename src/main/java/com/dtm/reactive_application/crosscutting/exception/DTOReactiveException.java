package com.dtm.reactive_application.crosscutting.exception;

import com.dtm.reactive_application.crosscutting.exception.enums.Layer;

public class DTOReactiveException extends ReactiveApplicationException {
    private static final long serialVersionUID = 1L;

    public DTOReactiveException(final String userMessage, final String technicalMessage, final Exception rootException) {
        super(userMessage, technicalMessage, rootException, Layer.DTO);
    }

    public static DTOReactiveException create(final String userMessage, final String technicalMessage, final Exception rootException) {
        return new DTOReactiveException(userMessage, technicalMessage, rootException);
    }

    public static DTOReactiveException create(final String userMessage, final String technicalMessage) {
        return new DTOReactiveException(userMessage, technicalMessage, new Exception());
    }

    public static DTOReactiveException create(final String userMessage) {
        return new DTOReactiveException(userMessage, userMessage, new Exception());
    }
}
