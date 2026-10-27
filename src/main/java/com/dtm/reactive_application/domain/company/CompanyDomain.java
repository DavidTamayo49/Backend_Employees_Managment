package com.dtm.reactive_application.domain.company;

import com.dtm.reactive_application.crosscutting.helpers.TextHelper;
import com.dtm.reactive_application.domain.Domain;

import java.util.UUID;

public final class CompanyDomain extends Domain {

    private final String name;
    private final String nit;

    private CompanyDomain(final UUID id, final String name, final String nit) {
        super(id);
        this.name = TextHelper.applyTrim(name);
        this.nit = TextHelper.applyTrim(nit);
    }

    public static CompanyDomain create(final UUID id, final String name, final String nit) {
        return new CompanyDomain(id, name, nit);
    }

    public String getName() {
        return name;
    }

    public String getNit() {
        return nit;
    }
}
