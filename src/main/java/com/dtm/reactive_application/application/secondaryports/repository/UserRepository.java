package com.dtm.reactive_application.application.secondaryports.repository;

import com.dtm.reactive_application.domain.user.UserDetailDomain;
import com.dtm.reactive_application.domain.user.UserDomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository {

    Mono<Boolean> existsByDocumentTypeIdAndDocument(UUID documentTypeId, String document);

    Mono<UserDomain> save(UserDomain user);

    Flux<UserDetailDomain> findAllDetailed();

    Flux<UserDetailDomain> findDetailedByCompanyId(UUID companyId);

    Mono<Boolean> existsById(UUID id);

    Mono<Void> deleteById(UUID id);
}
