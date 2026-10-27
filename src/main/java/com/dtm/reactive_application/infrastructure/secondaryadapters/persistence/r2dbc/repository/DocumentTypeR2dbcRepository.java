package com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.repository;

import com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.entity.DocumentTypeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface DocumentTypeR2dbcRepository extends ReactiveCrudRepository<DocumentTypeEntity, UUID> {

    Flux<DocumentTypeEntity> findAllByOrderByNameAsc();
}
