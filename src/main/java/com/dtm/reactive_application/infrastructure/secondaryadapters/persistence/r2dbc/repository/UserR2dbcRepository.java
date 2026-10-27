package com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.repository;

import com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserR2dbcRepository extends ReactiveCrudRepository<UserEntity, UUID> {

    Mono<Boolean> existsByDocumentTypeIdAndDocument(UUID documentTypeId, String document);

    Mono<Boolean> existsByDocumentTypeIdAndDocumentAndIdNot(UUID documentTypeId, String document, UUID id);
}
