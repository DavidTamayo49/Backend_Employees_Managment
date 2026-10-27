package com.dtm.reactive_application.domain.user;

import com.dtm.reactive_application.crosscutting.helpers.TextHelper;
import com.dtm.reactive_application.domain.Domain;
import com.dtm.reactive_application.domain.company.CompanyDomain;
import com.dtm.reactive_application.domain.documenttype.DocumentTypeDomain;
import com.dtm.reactive_application.domain.role.RoleDomain;

import java.util.UUID;

// Vista de lectura del empleado con sus relaciones resueltas; UserDomain sigue siendo el modelo de escritura
public final class UserDetailDomain extends Domain {

    private final String name;
    private final String document;
    private final DocumentTypeDomain documentType;
    private final RoleDomain role;
    private final CompanyDomain company;

    private UserDetailDomain(final UUID id, final String name, final String document,
                             final DocumentTypeDomain documentType, final RoleDomain role,
                             final CompanyDomain company) {
        super(id);
        this.name = TextHelper.applyTrim(name);
        this.document = TextHelper.applyTrim(document);
        this.documentType = documentType;
        this.role = role;
        this.company = company;
    }

    public static UserDetailDomain create(final UUID id, final String name, final String document,
                                          final DocumentTypeDomain documentType, final RoleDomain role,
                                          final CompanyDomain company) {
        return new UserDetailDomain(id, name, document, documentType, role, company);
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public DocumentTypeDomain getDocumentType() {
        return documentType;
    }

    public RoleDomain getRole() {
        return role;
    }

    public CompanyDomain getCompany() {
        return company;
    }
}
