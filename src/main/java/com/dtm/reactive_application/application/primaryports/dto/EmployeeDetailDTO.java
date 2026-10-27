package com.dtm.reactive_application.application.primaryports.dto;

import java.util.UUID;

public record EmployeeDetailDTO(UUID id, String name, String document, CatalogItemDTO documentType,
                                CatalogItemDTO role, CompanyDTO company) {
}
