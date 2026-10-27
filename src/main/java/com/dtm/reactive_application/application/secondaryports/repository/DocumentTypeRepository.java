package com.dtm.reactive_application.application.secondaryports.repository;

import com.dtm.reactive_application.domain.documenttype.DocumentTypeDomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface DocumentTypeRepository {

    Mono<Boolean> existsById(UUID id);

    Flux<DocumentTypeDomain> findAll();
}
