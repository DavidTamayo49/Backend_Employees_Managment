package com.dtm.reactive_application.domain.documenttype;

import com.dtm.reactive_application.crosscutting.helpers.TextHelper;
import com.dtm.reactive_application.domain.Domain;

import java.util.UUID;

public final class DocumentTypeDomain extends Domain {

    private final String name;

    private DocumentTypeDomain(final UUID id, final String name) {
        super(id);
        this.name = TextHelper.applyTrim(name);
    }

    public static DocumentTypeDomain create(final UUID id, final String name) {
        return new DocumentTypeDomain(id, name);
    }

    public String getName() {
        return name;
    }
}
