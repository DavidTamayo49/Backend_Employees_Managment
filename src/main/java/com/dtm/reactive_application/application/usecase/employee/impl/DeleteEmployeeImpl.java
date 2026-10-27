package com.dtm.reactive_application.application.usecase.employee.impl;

import com.dtm.reactive_application.application.secondaryports.repository.UserRepository;
import com.dtm.reactive_application.application.usecase.employee.DeleteEmployee;
import com.dtm.reactive_application.domain.user.rules.UserDoesExistRule;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class DeleteEmployeeImpl implements DeleteEmployee {

    private final UserDoesExistRule userDoesExistRule;
    private final UserRepository userRepository;

    public DeleteEmployeeImpl(final UserDoesExistRule userDoesExistRule, final UserRepository userRepository) {
        this.userDoesExistRule = userDoesExistRule;
        this.userRepository = userRepository;
    }

    @Override
    public Mono<Void> execute(final UUID id) {
        return userDoesExistRule.validate(id)
                .then(Mono.defer(() -> userRepository.deleteById(id)));
    }
}
