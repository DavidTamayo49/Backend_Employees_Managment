package com.dtm.reactive_application.domain.user.rules.impl;

import com.dtm.reactive_application.application.secondaryports.repository.UserRepository;
import com.dtm.reactive_application.domain.user.exceptions.UserDoesNotExistsException;
import com.dtm.reactive_application.domain.user.rules.UserDoesExistRule;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserDoesExistRuleImpl implements UserDoesExistRule {

    private final UserRepository userRepository;

    public UserDoesExistRuleImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<Void> validate(final UUID data) {
        return userRepository.existsById(data)
                .flatMap(exists -> exists ? Mono.<Void>empty() : Mono.<Void>error(UserDoesNotExistsException.create()));
    }
}
