package com.dtm.reactive_application.application.secondaryports.repository;

import com.dtm.reactive_application.domain.user.UserDomain;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository {

    Mono<Boolean> existsByDocumentTypeIdAndDocument(UUID documentTypeId, String document);

    Mono<UserDomain> save(UserDomain user);
}
