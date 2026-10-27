package com.dtm.reactive_application.application.primaryports.interactor.employee.impl;

import com.dtm.reactive_application.application.primaryports.dto.EmployeeDTO;
import com.dtm.reactive_application.application.primaryports.dto.ModifyEmployeeDTO;
import com.dtm.reactive_application.application.primaryports.interactor.employee.ModifyEmployeeInteractor;
import com.dtm.reactive_application.application.primaryports.mapper.EmployeeDTOMapper;
import com.dtm.reactive_application.application.usecase.employee.ModifyEmployee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class ModifyEmployeeInteractorImpl implements ModifyEmployeeInteractor {

    private final ModifyEmployee modifyEmployee;

    public ModifyEmployeeInteractorImpl(final ModifyEmployee modifyEmployee) {
        this.modifyEmployee = modifyEmployee;
    }

    @Override
    public Mono<EmployeeDTO> execute(final ModifyEmployeeDTO data) {
        return Mono.fromSupplier(() -> EmployeeDTOMapper.toModificationDomain(data))
                .flatMap(modifyEmployee::execute)
                .map(EmployeeDTOMapper::toDTO);
    }
}
