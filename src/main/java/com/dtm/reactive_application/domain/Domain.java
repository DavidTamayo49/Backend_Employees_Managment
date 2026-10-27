package com.dtm.reactive_application.domain;

import com.dtm.reactive_application.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public class Domain {

    private UUID id;

    protected Domain(final UUID id) {
        setId(id);
    }

    public final UUID getId() {
        return id;
    }

    private void setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
    }

    public void generateId() {
        this.id = UUIDHelper.generate();
    }
}
