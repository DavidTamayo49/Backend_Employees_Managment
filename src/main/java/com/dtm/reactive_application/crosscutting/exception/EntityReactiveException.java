package com.dtm.reactive_application.crosscutting.exception;

import com.dtm.reactive_application.crosscutting.exception.enums.Layer;

public class EntityReactiveException extends ReactiveApplicationException {
    private static final long serialVersionUID = 1L;

    public EntityReactiveException(final String userMessage, final String technicalMessage, final Exception rootException) {
        super(userMessage, technicalMessage, rootException, Layer.ENTITY);
    }

    public static EntityReactiveException create(final String userMessage, final String technicalMessage, final Exception rootException) {
        return new EntityReactiveException(userMessage, technicalMessage, rootException);
    }

    public static EntityReactiveException create(final String userMessage, final String technicalMessage) {
        return new EntityReactiveException(userMessage, technicalMessage, new Exception());
    }

    public static EntityReactiveException create(final String userMessage) {
        return new EntityReactiveException(userMessage, userMessage, new Exception());
    }
}
