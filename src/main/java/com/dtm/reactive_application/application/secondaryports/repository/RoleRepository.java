package com.dtm.reactive_application.application.secondaryports.repository;

import com.dtm.reactive_application.domain.role.RoleDomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RoleRepository {

    Mono<Boolean> existsById(UUID id);

    Flux<RoleDomain> findAll();
}
