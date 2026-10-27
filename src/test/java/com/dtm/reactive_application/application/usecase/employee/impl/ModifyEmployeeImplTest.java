package com.dtm.reactive_application.application.usecase.employee.impl;

import com.dtm.reactive_application.application.secondaryports.repository.UserRepository;
import com.dtm.reactive_application.application.usecase.employee.ModifyEmployeeRulesValidator;
import com.dtm.reactive_application.domain.user.UserDomain;
import com.dtm.reactive_application.domain.user.UserModificationDomain;
import com.dtm.reactive_application.domain.user.exceptions.UserDoesNotExistsException;
import com.dtm.reactive_application.domain.user.exceptions.UserModificationIsEmptyException;
import com.dtm.reactive_application.domain.user.exceptions.UserNameIsNotValidException;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class ModifyEmployeeImplTest {

    private final ModifyEmployeeRulesValidator rulesValidator = mock(ModifyEmployeeRulesValidator.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final ModifyEmployeeImpl useCase = new ModifyEmployeeImpl(rulesValidator, userRepository);

    private final UUID id = UUID.randomUUID();
    private final UserDomain current = UserDomain.create(id, "Ana Pérez", "1020304050",
            UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

    @Test
    void changesOnlyTheFieldsThatWereSent() {
        final var newRole = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Mono.just(current));
        when(rulesValidator.validate(any())).thenReturn(Mono.empty());
        when(userRepository.update(any())).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        StepVerifier.create(useCase.execute(UserModificationDomain.create(id, "Ana María Pérez", null, null, newRole, null)))
                .assertNext(modified -> {
                    assertEquals(id, modified.getId());
                    assertEquals("Ana María Pérez", modified.getName());
                    assertEquals(newRole, modified.getRoleId());
                    assertEquals(current.getDocument(), modified.getDocument());
                    assertEquals(current.getDocumentTypeId(), modified.getDocumentTypeId());
                    assertEquals(current.getCompanyId(), modified.getCompanyId());
                })
                .verifyComplete();
    }

    @Test
    void failsWhenNoFieldIsSent() {
        StepVerifier.create(useCase.execute(UserModificationDomain.create(id, null, null, null, null, null)))
                .expectError(UserModificationIsEmptyException.class)
                .verify();

        verifyNoInteractions(userRepository);
    }

    @Test
    void failsWhenTheEmployeeDoesNotExist() {
        when(userRepository.findById(id)).thenReturn(Mono.empty());

        StepVerifier.create(useCase.execute(UserModificationDomain.create(id, "Nuevo", null, null, null, null)))
                .expectError(UserDoesNotExistsException.class)
                .verify();

        verify(userRepository, never()).update(any());
    }

    @Test
    void doesNotUpdateWhenARuleFails() {
        when(userRepository.findById(id)).thenReturn(Mono.just(current));
        when(rulesValidator.validate(any())).thenReturn(Mono.error(UserNameIsNotValidException.create()));

        StepVerifier.create(useCase.execute(UserModificationDomain.create(id, " ", null, null, null, null)))
                .expectError(UserNameIsNotValidException.class)
                .verify();

        verify(userRepository, never()).update(any());
    }
}
