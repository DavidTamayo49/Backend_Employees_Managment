package com.dtm.reactive_application.application.primaryports.dto;

import java.util.UUID;

// Todos los campos son opcionales: solo se modifican los que lleguen con valor
public record ModifyEmployeeDTO(UUID id, String name, String document, UUID documentTypeId, UUID roleId,
                                UUID companyId) {

    public ModifyEmployeeDTO withId(final UUID newId) {
        return new ModifyEmployeeDTO(newId, name, document, documentTypeId, roleId, companyId);
    }
}
