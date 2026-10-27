package com.dtm.reactive_application.application.primaryports.interactor.company.impl;

import com.dtm.reactive_application.application.primaryports.dto.CompanyDTO;
import com.dtm.reactive_application.application.primaryports.interactor.company.RetrieveCompaniesInteractor;
import com.dtm.reactive_application.application.usecase.company.RetrieveCompanies;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RetrieveCompaniesInteractorImpl implements RetrieveCompaniesInteractor {

    private final RetrieveCompanies retrieveCompanies;

    public RetrieveCompaniesInteractorImpl(final RetrieveCompanies retrieveCompanies) {
        this.retrieveCompanies = retrieveCompanies;
    }

    @Override
    public Flux<CompanyDTO> execute(final Void data) {
        return retrieveCompanies.execute(null).map(item -> new CompanyDTO(item.getId(), item.getName(), item.getNit()));
    }
}
