package com.dtm.reactive_application.application.primaryports.interactor.role.impl;

import com.dtm.reactive_application.application.primaryports.dto.CatalogItemDTO;
import com.dtm.reactive_application.application.primaryports.interactor.role.RetrieveRolesInteractor;
import com.dtm.reactive_application.application.usecase.role.RetrieveRoles;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RetrieveRolesInteractorImpl implements RetrieveRolesInteractor {

    private final RetrieveRoles retrieveRoles;

    public RetrieveRolesInteractorImpl(final RetrieveRoles retrieveRoles) {
        this.retrieveRoles = retrieveRoles;
    }

    @Override
    public Flux<CatalogItemDTO> execute(final Void data) {
        return retrieveRoles.execute(null).map(item -> new CatalogItemDTO(item.getId(), item.getName()));
    }
}
