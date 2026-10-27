package com.dtm.reactive_application.crosscutting.exception;

import com.dtm.reactive_application.crosscutting.exception.enums.Layer;
import com.dtm.reactive_application.crosscutting.helpers.ObjectHelper;
import com.dtm.reactive_application.crosscutting.helpers.TextHelper;

public class ReactiveApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String userMessage;
    private final Layer layer;

    public ReactiveApplicationException(final String userMessage,
                                        final String technicalMessage, final Exception rootException,
                                        final Layer layer) {
        super(ObjectHelper.getDefault(technicalMessage, TextHelper.applyTrim(userMessage)),
                ObjectHelper.getDefault(rootException, new Exception()));
        this.userMessage = TextHelper.applyTrim(userMessage);
        this.layer = ObjectHelper.getDefault(layer, Layer.GENERAL);
    }

    public String getUserMessage() {
        return userMessage;
    }

    public Layer getLayer() {
        return layer;
    }
}
