package com.dtm.reactive_application.application.usecase.employee.impl;

import com.dtm.reactive_application.application.secondaryports.repository.UserRepository;
import com.dtm.reactive_application.application.usecase.employee.RetrieveEmployees;
import com.dtm.reactive_application.crosscutting.helpers.ObjectHelper;
import com.dtm.reactive_application.domain.user.UserDetailDomain;
import com.dtm.reactive_application.domain.user.rules.CompanyDoesExistRule;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Service
public class RetrieveEmployeesImpl implements RetrieveEmployees {

    private final UserRepository userRepository;
    private final CompanyDoesExistRule companyDoesExistRule;

    public RetrieveEmployeesImpl(final UserRepository userRepository,
                                 final CompanyDoesExistRule companyDoesExistRule) {
        this.userRepository = userRepository;
        this.companyDoesExistRule = companyDoesExistRule;
    }

    @Override
    public Flux<UserDetailDomain> execute(final UUID companyId) {
        if (ObjectHelper.isNull(companyId)) {
            return userRepository.findAllDetailed();
        }
        // Una empresa inexistente es un error del cliente, no una lista vacía
        return companyDoesExistRule.validate(companyId)
                .thenMany(Flux.defer(() -> userRepository.findDetailedByCompanyId(companyId)));
    }
}
