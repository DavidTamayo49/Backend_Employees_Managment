package com.dtm.reactive_application.application.usecase.role;

import com.dtm.reactive_application.application.usecase.UseCaseWithFluxReturn;
import com.dtm.reactive_application.domain.role.RoleDomain;

public interface RetrieveRoles extends UseCaseWithFluxReturn<Void, RoleDomain> {
}
