package com.dtm.reactive_application.application.usecase.employee.impl;

import com.dtm.reactive_application.application.secondaryports.repository.UserRepository;
import com.dtm.reactive_application.domain.company.CompanyDomain;
import com.dtm.reactive_application.domain.documenttype.DocumentTypeDomain;
import com.dtm.reactive_application.domain.role.RoleDomain;
import com.dtm.reactive_application.domain.user.UserDetailDomain;
import com.dtm.reactive_application.domain.user.exceptions.CompanyDoesNotExistsException;
import com.dtm.reactive_application.domain.user.rules.CompanyDoesExistRule;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class RetrieveEmployeesImplTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final CompanyDoesExistRule companyDoesExistRule = mock(CompanyDoesExistRule.class);
    private final RetrieveEmployeesImpl useCase = new RetrieveEmployeesImpl(userRepository, companyDoesExistRule);

    private UserDetailDomain employee(final UUID companyId) {
        return UserDetailDomain.create(UUID.randomUUID(), "Ana Pérez", "1020304050",
                DocumentTypeDomain.create(UUID.randomUUID(), "Cédula de ciudadanía"),
                RoleDomain.create(UUID.randomUUID(), "Operario"),
                CompanyDomain.create(companyId, "Empresa Demo S.A.S.", "900123456-7"));
    }

    @Test
    void listsAllEmployeesWhenNoCompanyIsGiven() {
        when(userRepository.findAllDetailed()).thenReturn(Flux.just(employee(UUID.randomUUID()), employee(UUID.randomUUID())));

        StepVerifier.create(useCase.execute(null))
                .expectNextCount(2)
                .verifyComplete();

        verifyNoInteractions(companyDoesExistRule);
    }

    @Test
    void listsOnlyEmployeesOfTheGivenCompany() {
        final var companyId = UUID.randomUUID();
        when(companyDoesExistRule.validate(companyId)).thenReturn(Mono.empty());
        when(userRepository.findDetailedByCompanyId(companyId)).thenReturn(Flux.just(employee(companyId)));

        StepVerifier.create(useCase.execute(companyId))
                .expectNextMatches(user -> user.getCompany().getId().equals(companyId))
                .verifyComplete();
    }

    @Test
    void failsWhenTheCompanyDoesNotExist() {
        final var companyId = UUID.randomUUID();
        when(companyDoesExistRule.validate(companyId)).thenReturn(Mono.error(CompanyDoesNotExistsException.create()));

        StepVerifier.create(useCase.execute(companyId))
                .expectError(CompanyDoesNotExistsException.class)
                .verify();

        verify(userRepository, never()).findDetailedByCompanyId(any());
    }
}
