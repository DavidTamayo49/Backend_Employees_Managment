package com.dtm.reactive_application.application.usecase.employee.impl;

import com.dtm.reactive_application.application.secondaryports.repository.UserRepository;
import com.dtm.reactive_application.application.usecase.employee.RegisterNewEmployeeRulesValidator;
import com.dtm.reactive_application.crosscutting.helpers.UUIDHelper;
import com.dtm.reactive_application.domain.user.UserDomain;
import com.dtm.reactive_application.domain.user.exceptions.UserDocumentDoesExistsException;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RegisterNewEmployeeImplTest {

    private final RegisterNewEmployeeRulesValidator rulesValidator = mock(RegisterNewEmployeeRulesValidator.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final RegisterNewEmployeeImpl useCase = new RegisterNewEmployeeImpl(rulesValidator, userRepository);

    private UserDomain newEmployee() {
        return UserDomain.create(UUIDHelper.getDefault(), "Ana Pérez", "1020304050",
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
    }

    @Test
    void savesEmployeeWithGeneratedIdWhenRulesPass() {
        when(rulesValidator.validate(any())).thenReturn(Mono.empty());
        when(userRepository.save(any(UserDomain.class))).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        StepVerifier.create(useCase.execute(newEmployee()))
                .assertNext(saved -> assertFalse(UUIDHelper.isDefault(saved.getId())))
                .verifyComplete();
    }

    @Test
    void doesNotSaveWhenARuleFails() {
        when(rulesValidator.validate(any())).thenReturn(Mono.error(UserDocumentDoesExistsException.create()));

        StepVerifier.create(useCase.execute(newEmployee()))
                .expectError(UserDocumentDoesExistsException.class)
                .verify();

        verify(userRepository, never()).save(any());
    }
}
