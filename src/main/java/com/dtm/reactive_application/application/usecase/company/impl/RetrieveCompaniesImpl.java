package com.dtm.reactive_application.application.usecase.company.impl;

import com.dtm.reactive_application.application.secondaryports.repository.CompanyRepository;
import com.dtm.reactive_application.application.usecase.company.RetrieveCompanies;
import com.dtm.reactive_application.domain.company.CompanyDomain;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RetrieveCompaniesImpl implements RetrieveCompanies {

    private final CompanyRepository repository;

    public RetrieveCompaniesImpl(final CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<CompanyDomain> execute(final Void domain) {
        return repository.findAll();
    }
}
