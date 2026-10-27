package com.dtm.reactive_application.application.usecase.company.impl;

import com.dtm.reactive_application.application.secondaryports.repository.CompanyRepository;
import com.dtm.reactive_application.domain.company.CompanyDomain;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RetrieveCompaniesImplTest {

    private final CompanyRepository companyRepository = mock(CompanyRepository.class);
    private final RetrieveCompaniesImpl useCase = new RetrieveCompaniesImpl(companyRepository);

    @Test
    void returnsAllCompanies() {
        when(companyRepository.findAll()).thenReturn(Flux.just(
                CompanyDomain.create(UUID.randomUUID(), "Empresa Demo S.A.S.", "900123456-7")));

        StepVerifier.create(useCase.execute(null))
                .expectNextMatches(company -> company.getNit().equals("900123456-7"))
                .verifyComplete();
    }
}
