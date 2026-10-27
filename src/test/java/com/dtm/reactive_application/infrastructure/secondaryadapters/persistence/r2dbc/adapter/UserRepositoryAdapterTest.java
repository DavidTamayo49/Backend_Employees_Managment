package com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.adapter;

import com.dtm.reactive_application.domain.user.UserDomain;
import com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.entity.UserEntity;
import com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.repository.UserDetailQuery;
import com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.repository.UserR2dbcRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRepositoryAdapterTest {

    private final UserR2dbcRepository r2dbcRepository = mock(UserR2dbcRepository.class);
    private final UserRepositoryAdapter adapter = new UserRepositoryAdapter(r2dbcRepository, mock(UserDetailQuery.class));

    @Test
    void savesAsNewEntityAndMapsBackToDomain() {
        final var user = UserDomain.create(UUID.randomUUID(), "Ana Pérez", "1020304050",
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        when(r2dbcRepository.save(any(UserEntity.class))).thenAnswer(invocation -> {
            final UserEntity entity = invocation.getArgument(0);
            // El id viene del dominio, así que debe marcarse como nueva para que R2DBC haga INSERT
            assertTrue(entity.isNew());
            return Mono.just(entity);
        });

        StepVerifier.create(adapter.save(user))
                .assertNext(saved -> {
                    assertEquals(user.getId(), saved.getId());
                    assertEquals(user.getDocument(), saved.getDocument());
                })
                .verifyComplete();
    }
}
