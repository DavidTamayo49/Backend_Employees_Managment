package com.dtm.reactive_application.application.primaryports.mapper;

import com.dtm.reactive_application.application.primaryports.dto.EmployeeDTO;
import com.dtm.reactive_application.application.primaryports.dto.RegisterNewEmployeeDTO;
import com.dtm.reactive_application.crosscutting.helpers.UUIDHelper;
import com.dtm.reactive_application.domain.user.UserDomain;

public final class EmployeeDTOMapper {

    private EmployeeDTOMapper() {
    }

    public static UserDomain toDomain(final RegisterNewEmployeeDTO dto) {
        return UserDomain.create(UUIDHelper.getDefault(), dto.name(), dto.document(),
                dto.documentTypeId(), dto.roleId(), dto.companyId());
    }

    public static EmployeeDTO toDTO(final UserDomain domain) {
        return new EmployeeDTO(domain.getId(), domain.getName(), domain.getDocument(),
                domain.getDocumentTypeId(), domain.getRoleId(), domain.getCompanyId());
    }
}
