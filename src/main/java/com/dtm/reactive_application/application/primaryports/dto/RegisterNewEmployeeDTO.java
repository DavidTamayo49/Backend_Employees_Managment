package com.dtm.reactive_application.application.primaryports.dto;

import java.util.UUID;

public record RegisterNewEmployeeDTO(String name, String document, UUID documentTypeId, UUID roleId, UUID companyId) {
}
