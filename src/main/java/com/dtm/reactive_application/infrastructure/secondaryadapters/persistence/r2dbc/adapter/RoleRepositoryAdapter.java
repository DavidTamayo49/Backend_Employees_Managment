package com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.adapter;

import com.dtm.reactive_application.application.secondaryports.repository.RoleRepository;
import com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.repository.RoleR2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class RoleRepositoryAdapter implements RoleRepository {

    private final RoleR2dbcRepository r2dbcRepository;

    public RoleRepositoryAdapter(final RoleR2dbcRepository r2dbcRepository) {
        this.r2dbcRepository = r2dbcRepository;
    }

    @Override
    public Mono<Boolean> existsById(final UUID id) {
        return r2dbcRepository.existsById(id);
    }
}
