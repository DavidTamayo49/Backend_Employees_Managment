package com.dtm.reactive_application.domain.user;

import com.dtm.reactive_application.crosscutting.helpers.ObjectHelper;

import java.util.UUID;

// Cambios parciales (PATCH) sobre un empleado: un campo en null significa "no modificar"
public final class UserModificationDomain {

    private final UUID id;
    private final String name;
    private final String document;
    private final UUID documentTypeId;
    private final UUID roleId;
    private final UUID companyId;

    private UserModificationDomain(final UUID id, final String name, final String document,
                                   final UUID documentTypeId, final UUID roleId, final UUID companyId) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.documentTypeId = documentTypeId;
        this.roleId = roleId;
        this.companyId = companyId;
    }

    public static UserModificationDomain create(final UUID id, final String name, final String document,
                                                final UUID documentTypeId, final UUID roleId, final UUID companyId) {
        return new UserModificationDomain(id, name, document, documentTypeId, roleId, companyId);
    }

    public UUID getId() {
        return id;
    }

    public boolean hasChanges() {
        return !ObjectHelper.isNull(name) || !ObjectHelper.isNull(document) || !ObjectHelper.isNull(documentTypeId)
                || !ObjectHelper.isNull(roleId) || !ObjectHelper.isNull(companyId);
    }

    public UserDomain applyTo(final UserDomain current) {
        return UserDomain.create(current.getId(),
                ObjectHelper.getDefault(name, current.getName()),
                ObjectHelper.getDefault(document, current.getDocument()),
                ObjectHelper.getDefault(documentTypeId, current.getDocumentTypeId()),
                ObjectHelper.getDefault(roleId, current.getRoleId()),
                ObjectHelper.getDefault(companyId, current.getCompanyId()));
    }
}
