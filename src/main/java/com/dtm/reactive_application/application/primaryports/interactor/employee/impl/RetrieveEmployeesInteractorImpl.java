package com.dtm.reactive_application.application.primaryports.interactor.employee.impl;

import com.dtm.reactive_application.application.primaryports.dto.EmployeeDetailDTO;
import com.dtm.reactive_application.application.primaryports.interactor.employee.RetrieveEmployeesInteractor;
import com.dtm.reactive_application.application.primaryports.mapper.EmployeeDTOMapper;
import com.dtm.reactive_application.application.usecase.employee.RetrieveEmployees;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Service
public class RetrieveEmployeesInteractorImpl implements RetrieveEmployeesInteractor {

    private final RetrieveEmployees retrieveEmployees;

    public RetrieveEmployeesInteractorImpl(final RetrieveEmployees retrieveEmployees) {
        this.retrieveEmployees = retrieveEmployees;
    }

    @Override
    public Flux<EmployeeDetailDTO> execute(final UUID companyId) {
        return retrieveEmployees.execute(companyId).map(EmployeeDTOMapper::toDetailDTO);
    }
}
