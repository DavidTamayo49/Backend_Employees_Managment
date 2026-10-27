package com.dtm.reactive_application.application.secondaryports.repository;

import com.dtm.reactive_application.domain.company.CompanyDomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CompanyRepository {

    Mono<Boolean> existsById(UUID id);

    Flux<CompanyDomain> findAll();
}
