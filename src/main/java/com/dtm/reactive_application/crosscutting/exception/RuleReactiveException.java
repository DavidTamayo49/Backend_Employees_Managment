package com.dtm.reactive_application.crosscutting.exception;

import com.dtm.reactive_application.crosscutting.exception.enums.Layer;

public class RuleReactiveException extends ReactiveApplicationException {
    private static final long serialVersionUID = 1L;

    public RuleReactiveException(final String userMessage, final String technicalMessage, final Exception rootException) {
        super(userMessage, technicalMessage, rootException, Layer.RULE);
    }

    public static RuleReactiveException create(final String userMessage, final String technicalMessage, final Exception rootException) {
        return new RuleReactiveException(userMessage, technicalMessage, rootException);
    }

    public static RuleReactiveException create(final String userMessage, final String technicalMessage) {
        return new RuleReactiveException(userMessage, technicalMessage, new Exception());
    }

    public static RuleReactiveException create(final String userMessage) {
        return new RuleReactiveException(userMessage, userMessage, new Exception());
    }
}
