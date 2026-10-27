package com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.adapter;

import com.dtm.reactive_application.application.secondaryports.repository.UserRepository;
import com.dtm.reactive_application.domain.user.UserDomain;
import com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.mapper.UserEntityMapper;
import com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.repository.UserR2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserR2dbcRepository r2dbcRepository;

    public UserRepositoryAdapter(final UserR2dbcRepository r2dbcRepository) {
        this.r2dbcRepository = r2dbcRepository;
    }

    @Override
    public Mono<Boolean> existsByDocumentTypeIdAndDocument(final UUID documentTypeId, final String document) {
        return r2dbcRepository.existsByDocumentTypeIdAndDocument(documentTypeId, document);
    }

    @Override
    public Mono<UserDomain> save(final UserDomain user) {
        return r2dbcRepository.save(UserEntityMapper.toNewEntity(user))
                .map(UserEntityMapper::toDomain);
    }
}
