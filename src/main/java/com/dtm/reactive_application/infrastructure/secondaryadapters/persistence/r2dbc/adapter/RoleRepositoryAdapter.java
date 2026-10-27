package com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.adapter;

import com.dtm.reactive_application.application.secondaryports.repository.RoleRepository;
import com.dtm.reactive_application.domain.role.RoleDomain;
import com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.repository.RoleR2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
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

    @Override
    public Flux<RoleDomain> findAll() {
        return r2dbcRepository.findAllByOrderByNameAsc()
                .map(entity -> RoleDomain.create(entity.getId(), entity.getName()));
    }
}
