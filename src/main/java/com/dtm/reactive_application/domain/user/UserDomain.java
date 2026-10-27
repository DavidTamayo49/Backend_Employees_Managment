package com.dtm.reactive_application.domain.user;

import com.dtm.reactive_application.crosscutting.helpers.TextHelper;
import com.dtm.reactive_application.crosscutting.helpers.UUIDHelper;
import com.dtm.reactive_application.domain.Domain;

import java.util.UUID;

public final class UserDomain extends Domain {

    private final String name;
    private final String document;
    private final UUID documentTypeId;
    private final UUID roleId;
    private final UUID companyId;

    private UserDomain(final UUID id, final String name, final String document,
                       final UUID documentTypeId, final UUID roleId, final UUID companyId) {
        super(id);
        this.name = TextHelper.applyTrim(name);
        this.document = TextHelper.applyTrim(document);
        this.documentTypeId = UUIDHelper.getDefault(documentTypeId, UUIDHelper.getDefault());
        this.roleId = UUIDHelper.getDefault(roleId, UUIDHelper.getDefault());
        this.companyId = UUIDHelper.getDefault(companyId, UUIDHelper.getDefault());
    }

    public static UserDomain create(final UUID id, final String name, final String document,
                                    final UUID documentTypeId, final UUID roleId, final UUID companyId) {
        return new UserDomain(id, name, document, documentTypeId, roleId, companyId);
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public UUID getDocumentTypeId() {
        return documentTypeId;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public UUID getCompanyId() {
        return companyId;
    }
}
