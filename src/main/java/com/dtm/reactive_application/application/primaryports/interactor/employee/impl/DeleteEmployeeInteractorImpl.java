package com.dtm.reactive_application.application.primaryports.interactor.employee.impl;

import com.dtm.reactive_application.application.primaryports.interactor.employee.DeleteEmployeeInteractor;
import com.dtm.reactive_application.application.usecase.employee.DeleteEmployee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class DeleteEmployeeInteractorImpl implements DeleteEmployeeInteractor {

    private final DeleteEmployee deleteEmployee;

    public DeleteEmployeeInteractorImpl(final DeleteEmployee deleteEmployee) {
        this.deleteEmployee = deleteEmployee;
    }

    @Override
    public Mono<Void> execute(final UUID id) {
        return deleteEmployee.execute(id);
    }
}
