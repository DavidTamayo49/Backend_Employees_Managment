package com.dtm.reactive_application.domain.user.rules.impl;

import com.dtm.reactive_application.application.secondaryports.repository.RoleRepository;
import com.dtm.reactive_application.domain.user.exceptions.RoleDoesNotExistsException;
import com.dtm.reactive_application.domain.user.rules.RoleDoesExistRule;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class RoleDoesExistRuleImpl implements RoleDoesExistRule {

    private final RoleRepository repository;

    public RoleDoesExistRuleImpl(final RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> validate(final UUID data) {
        return repository.existsById(data)
                .flatMap(exists -> exists ? Mono.<Void>empty() : Mono.<Void>error(RoleDoesNotExistsException.create()));
    }
}
