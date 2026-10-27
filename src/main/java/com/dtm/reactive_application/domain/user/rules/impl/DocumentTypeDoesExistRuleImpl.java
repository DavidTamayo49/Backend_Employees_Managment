package com.dtm.reactive_application.domain.user.rules.impl;

import com.dtm.reactive_application.application.secondaryports.repository.DocumentTypeRepository;
import com.dtm.reactive_application.domain.user.exceptions.DocumentTypeDoesNotExistsException;
import com.dtm.reactive_application.domain.user.rules.DocumentTypeDoesExistRule;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class DocumentTypeDoesExistRuleImpl implements DocumentTypeDoesExistRule {

    private final DocumentTypeRepository repository;

    public DocumentTypeDoesExistRuleImpl(final DocumentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> validate(final UUID data) {
        return repository.existsById(data)
                .flatMap(exists -> exists ? Mono.<Void>empty() : Mono.<Void>error(DocumentTypeDoesNotExistsException.create()));
    }
}
