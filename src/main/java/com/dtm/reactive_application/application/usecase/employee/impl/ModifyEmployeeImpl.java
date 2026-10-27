package com.dtm.reactive_application.application.usecase.employee.impl;

import com.dtm.reactive_application.application.secondaryports.repository.UserRepository;
import com.dtm.reactive_application.application.usecase.employee.ModifyEmployee;
import com.dtm.reactive_application.application.usecase.employee.ModifyEmployeeRulesValidator;
import com.dtm.reactive_application.domain.user.UserDomain;
import com.dtm.reactive_application.domain.user.UserModificationDomain;
import com.dtm.reactive_application.domain.user.exceptions.UserDoesNotExistsException;
import com.dtm.reactive_application.domain.user.exceptions.UserModificationIsEmptyException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ModifyEmployeeImpl implements ModifyEmployee {

    private final ModifyEmployeeRulesValidator rulesValidator;
    private final UserRepository userRepository;

    public ModifyEmployeeImpl(final ModifyEmployeeRulesValidator rulesValidator, final UserRepository userRepository) {
        this.rulesValidator = rulesValidator;
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDomain> execute(final UserModificationDomain modification) {
        if (!modification.hasChanges()) {
            return Mono.error(UserModificationIsEmptyException.create());
        }
        return userRepository.findById(modification.getId())
                .switchIfEmpty(Mono.error(UserDoesNotExistsException::create))
                .map(modification::applyTo)
                .flatMap(modified -> rulesValidator.validate(modified)
                        .then(Mono.defer(() -> userRepository.update(modified))));
    }
}
