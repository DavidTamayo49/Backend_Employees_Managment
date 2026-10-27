package com.dtm.reactive_application.application.primaryports.interactor.documenttype.impl;

import com.dtm.reactive_application.application.primaryports.dto.CatalogItemDTO;
import com.dtm.reactive_application.application.primaryports.interactor.documenttype.RetrieveDocumentTypesInteractor;
import com.dtm.reactive_application.application.usecase.documenttype.RetrieveDocumentTypes;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RetrieveDocumentTypesInteractorImpl implements RetrieveDocumentTypesInteractor {

    private final RetrieveDocumentTypes retrieveDocumentTypes;

    public RetrieveDocumentTypesInteractorImpl(final RetrieveDocumentTypes retrieveDocumentTypes) {
        this.retrieveDocumentTypes = retrieveDocumentTypes;
    }

    @Override
    public Flux<CatalogItemDTO> execute(final Void data) {
        return retrieveDocumentTypes.execute(null).map(item -> new CatalogItemDTO(item.getId(), item.getName()));
    }
}
