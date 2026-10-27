package com.dtm.reactive_application.application.primaryports.interactor.employee;

import com.dtm.reactive_application.application.primaryports.dto.EmployeeDetailDTO;
import com.dtm.reactive_application.application.primaryports.interactor.InteractorWithFluxReturn;

import java.util.UUID;

public interface RetrieveEmployeesInteractor extends InteractorWithFluxReturn<UUID, EmployeeDetailDTO> {
}
