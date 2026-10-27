package com.dtm.reactive_application.application.secondaryports.repository;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface DocumentTypeRepository {

    Mono<Boolean> existsById(UUID id);
}
