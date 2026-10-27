package com.dtm.reactive_application.domain.user.rules.impl;

import com.dtm.reactive_application.application.secondaryports.repository.UserRepository;
import com.dtm.reactive_application.domain.user.UserDomain;
import com.dtm.reactive_application.domain.user.exceptions.UserDocumentDoesExistsException;
import com.dtm.reactive_application.domain.user.rules.UserDocumentIsNotUsedByAnotherUserRule;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDocumentIsNotUsedByAnotherUserRuleImpl implements UserDocumentIsNotUsedByAnotherUserRule {

    private final UserRepository userRepository;

    public UserDocumentIsNotUsedByAnotherUserRuleImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<Void> validate(final UserDomain data) {
        // Excluye al propio empleado para que conservar su documento no cuente como duplicado
        return userRepository.existsByDocumentTypeIdAndDocumentAndIdNot(data.getDocumentTypeId(), data.getDocument(), data.getId())
                .flatMap(exists -> exists
                        ? Mono.<Void>error(UserDocumentDoesExistsException.create())
                        : Mono.<Void>empty());
    }
}
