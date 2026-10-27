package com.dtm.reactive_application.domain.user.rules.impl;

import com.dtm.reactive_application.application.secondaryports.repository.UserRepository;
import com.dtm.reactive_application.domain.user.UserDomain;
import com.dtm.reactive_application.domain.user.exceptions.UserDocumentDoesExistsException;
import com.dtm.reactive_application.domain.user.rules.UserDocumentDoesNotExistRule;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDocumentDoesNotExistRuleImpl implements UserDocumentDoesNotExistRule {

    private final UserRepository userRepository;

    public UserDocumentDoesNotExistRuleImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<Void> validate(final UserDomain data) {
        return userRepository.existsByDocumentTypeIdAndDocument(data.getDocumentTypeId(), data.getDocument())
                .flatMap(exists -> exists
                        ? Mono.<Void>error(UserDocumentDoesExistsException.create())
                        : Mono.<Void>empty());
    }
}
