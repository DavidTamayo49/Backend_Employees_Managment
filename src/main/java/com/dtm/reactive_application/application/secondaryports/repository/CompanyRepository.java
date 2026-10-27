package com.dtm.reactive_application.application.secondaryports.repository;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CompanyRepository {

    Mono<Boolean> existsById(UUID id);
}
