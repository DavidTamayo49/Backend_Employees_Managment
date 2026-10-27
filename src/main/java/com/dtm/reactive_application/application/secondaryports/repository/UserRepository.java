package com.dtm.reactive_application.application.secondaryports.repository;

import com.dtm.reactive_application.domain.user.UserDetailDomain;
import com.dtm.reactive_application.domain.user.UserDomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository {

    Mono<Boolean> existsByDocumentTypeIdAndDocument(UUID documentTypeId, String document);

    Mono<Boolean> existsByDocumentTypeIdAndDocumentAndIdNot(UUID documentTypeId, String document, UUID id);

    Mono<UserDomain> save(UserDomain user);

    Mono<UserDomain> update(UserDomain user);

    Mono<UserDomain> findById(UUID id);

    Flux<UserDetailDomain> findAllDetailed();

    Flux<UserDetailDomain> findDetailedByCompanyId(UUID companyId);

    Mono<Boolean> existsById(UUID id);

    Mono<Void> deleteById(UUID id);
}
