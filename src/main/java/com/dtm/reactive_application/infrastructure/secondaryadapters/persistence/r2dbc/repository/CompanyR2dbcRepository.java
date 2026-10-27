package com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.repository;

import com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.entity.CompanyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface CompanyR2dbcRepository extends ReactiveCrudRepository<CompanyEntity, UUID> {

    Flux<CompanyEntity> findAllByOrderByNameAsc();
}
