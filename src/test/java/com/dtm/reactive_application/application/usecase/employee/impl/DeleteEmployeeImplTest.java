package com.dtm.reactive_application.application.usecase.employee.impl;

import com.dtm.reactive_application.application.secondaryports.repository.UserRepository;
import com.dtm.reactive_application.domain.user.exceptions.UserDoesNotExistsException;
import com.dtm.reactive_application.domain.user.rules.UserDoesExistRule;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteEmployeeImplTest {

    private final UserDoesExistRule userDoesExistRule = mock(UserDoesExistRule.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final DeleteEmployeeImpl useCase = new DeleteEmployeeImpl(userDoesExistRule, userRepository);

    @Test
    void deletesTheEmployeeWhenItExists() {
        final var id = UUID.randomUUID();
        when(userDoesExistRule.validate(id)).thenReturn(Mono.empty());
        when(userRepository.deleteById(id)).thenReturn(Mono.empty());

        StepVerifier.create(useCase.execute(id)).verifyComplete();

        verify(userRepository).deleteById(id);
    }

    @Test
    void doesNotDeleteWhenTheEmployeeDoesNotExist() {
        final var id = UUID.randomUUID();
        when(userDoesExistRule.validate(id)).thenReturn(Mono.error(UserDoesNotExistsException.create()));

        StepVerifier.create(useCase.execute(id))
                .expectError(UserDoesNotExistsException.class)
                .verify();

        verify(userRepository, never()).deleteById(any());
    }
}
