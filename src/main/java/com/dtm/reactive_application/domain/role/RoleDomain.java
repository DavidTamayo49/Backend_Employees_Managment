package com.dtm.reactive_application.domain.role;

import com.dtm.reactive_application.crosscutting.helpers.TextHelper;
import com.dtm.reactive_application.domain.Domain;

import java.util.UUID;

public final class RoleDomain extends Domain {

    private final String name;

    private RoleDomain(final UUID id, final String name) {
        super(id);
        this.name = TextHelper.applyTrim(name);
    }

    public static RoleDomain create(final UUID id, final String name) {
        return new RoleDomain(id, name);
    }

    public String getName() {
        return name;
    }
}
