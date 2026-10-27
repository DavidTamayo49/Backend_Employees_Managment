package com.dtm.reactive_application.application.primaryports.interactor.employee.impl;

import com.dtm.reactive_application.application.primaryports.dto.EmployeeDTO;
import com.dtm.reactive_application.application.primaryports.dto.RegisterNewEmployeeDTO;
import com.dtm.reactive_application.application.primaryports.interactor.employee.RegisterNewEmployeeInteractor;
import com.dtm.reactive_application.application.primaryports.mapper.EmployeeDTOMapper;
import com.dtm.reactive_application.application.usecase.employee.RegisterNewEmployee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class RegisterNewEmployeeInteractorImpl implements RegisterNewEmployeeInteractor {

    private final RegisterNewEmployee registerNewEmployee;

    public RegisterNewEmployeeInteractorImpl(final RegisterNewEmployee registerNewEmployee) {
        this.registerNewEmployee = registerNewEmployee;
    }

    @Override
    public Mono<EmployeeDTO> execute(final RegisterNewEmployeeDTO data) {
        return Mono.fromSupplier(() -> EmployeeDTOMapper.toDomain(data))
                .flatMap(registerNewEmployee::execute)
                .map(EmployeeDTOMapper::toDTO);
    }
}
