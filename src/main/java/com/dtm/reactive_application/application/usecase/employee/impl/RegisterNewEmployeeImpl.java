package com.dtm.reactive_application.application.usecase.employee.impl;

import com.dtm.reactive_application.application.secondaryports.repository.UserRepository;
import com.dtm.reactive_application.application.usecase.employee.RegisterNewEmployee;
import com.dtm.reactive_application.application.usecase.employee.RegisterNewEmployeeRulesValidator;
import com.dtm.reactive_application.domain.user.UserDomain;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RegisterNewEmployeeImpl implements RegisterNewEmployee {

    private final RegisterNewEmployeeRulesValidator rulesValidator;
    private final UserRepository userRepository;

    public RegisterNewEmployeeImpl(final RegisterNewEmployeeRulesValidator rulesValidator,
                                   final UserRepository userRepository) {
        this.rulesValidator = rulesValidator;
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDomain> execute(final UserDomain domain) {
        return rulesValidator.validate(domain)
                .then(Mono.defer(() -> {
                    domain.generateId();
                    return userRepository.save(domain);
                }));
    }
}
