package com.dtm.reactive_application.application.usecase.employee;

import com.dtm.reactive_application.application.usecase.UseCaseWithFluxReturn;
import com.dtm.reactive_application.domain.user.UserDetailDomain;

import java.util.UUID;

// Recibe el id de la empresa por la que filtrar; null lista todos los empleados
public interface RetrieveEmployees extends UseCaseWithFluxReturn<UUID, UserDetailDomain> {
}
