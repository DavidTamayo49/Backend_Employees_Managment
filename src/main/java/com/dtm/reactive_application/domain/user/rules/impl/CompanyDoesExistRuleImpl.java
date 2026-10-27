package com.dtm.reactive_application.domain.user.rules.impl;

import com.dtm.reactive_application.application.secondaryports.repository.CompanyRepository;
import com.dtm.reactive_application.domain.user.exceptions.CompanyDoesNotExistsException;
import com.dtm.reactive_application.domain.user.rules.CompanyDoesExistRule;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CompanyDoesExistRuleImpl implements CompanyDoesExistRule {

    private final CompanyRepository repository;

    public CompanyDoesExistRuleImpl(final CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> validate(final UUID data) {
        return repository.existsById(data)
                .flatMap(exists -> exists ? Mono.<Void>empty() : Mono.<Void>error(CompanyDoesNotExistsException.create()));
    }
}
