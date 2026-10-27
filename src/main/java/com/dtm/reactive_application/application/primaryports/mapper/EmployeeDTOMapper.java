package com.dtm.reactive_application.application.primaryports.mapper;

import com.dtm.reactive_application.application.primaryports.dto.CatalogItemDTO;
import com.dtm.reactive_application.application.primaryports.dto.CompanyDTO;
import com.dtm.reactive_application.application.primaryports.dto.EmployeeDTO;
import com.dtm.reactive_application.application.primaryports.dto.EmployeeDetailDTO;
import com.dtm.reactive_application.application.primaryports.dto.ModifyEmployeeDTO;
import com.dtm.reactive_application.application.primaryports.dto.RegisterNewEmployeeDTO;
import com.dtm.reactive_application.crosscutting.helpers.UUIDHelper;
import com.dtm.reactive_application.domain.user.UserDetailDomain;
import com.dtm.reactive_application.domain.user.UserDomain;
import com.dtm.reactive_application.domain.user.UserModificationDomain;

public final class EmployeeDTOMapper {

    private EmployeeDTOMapper() {
    }

    public static UserDomain toDomain(final RegisterNewEmployeeDTO dto) {
        return UserDomain.create(UUIDHelper.getDefault(), dto.name(), dto.document(),
                dto.documentTypeId(), dto.roleId(), dto.companyId());
    }

    public static UserModificationDomain toModificationDomain(final ModifyEmployeeDTO dto) {
        return UserModificationDomain.create(dto.id(), dto.name(), dto.document(),
                dto.documentTypeId(), dto.roleId(), dto.companyId());
    }

    public static EmployeeDTO toDTO(final UserDomain domain) {
        return new EmployeeDTO(domain.getId(), domain.getName(), domain.getDocument(),
                domain.getDocumentTypeId(), domain.getRoleId(), domain.getCompanyId());
    }

    public static EmployeeDetailDTO toDetailDTO(final UserDetailDomain domain) {
        final var documentType = domain.getDocumentType();
        final var role = domain.getRole();
        final var company = domain.getCompany();
        return new EmployeeDetailDTO(domain.getId(), domain.getName(), domain.getDocument(),
                new CatalogItemDTO(documentType.getId(), documentType.getName()),
                new CatalogItemDTO(role.getId(), role.getName()),
                new CompanyDTO(company.getId(), company.getName(), company.getNit()));
    }
}
