package com.dtm.reactive_application.application.usecase.documenttype.impl;

import com.dtm.reactive_application.application.secondaryports.repository.DocumentTypeRepository;
import com.dtm.reactive_application.application.usecase.documenttype.RetrieveDocumentTypes;
import com.dtm.reactive_application.domain.documenttype.DocumentTypeDomain;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RetrieveDocumentTypesImpl implements RetrieveDocumentTypes {

    private final DocumentTypeRepository repository;

    public RetrieveDocumentTypesImpl(final DocumentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<DocumentTypeDomain> execute(final Void domain) {
        return repository.findAll();
    }
}
