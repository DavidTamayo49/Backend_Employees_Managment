package com.dtm.reactive_application.infrastructure.primaryadapters.controller.rest.documenttype;

import com.dtm.reactive_application.application.primaryports.dto.CatalogItemDTO;
import com.dtm.reactive_application.application.primaryports.interactor.documenttype.RetrieveDocumentTypesInteractor;
import com.dtm.reactive_application.infrastructure.primaryadapters.controller.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/document-types")
public class RetrieveDocumentTypesController {

    private final RetrieveDocumentTypesInteractor retrieveDocumentTypesInteractor;

    public RetrieveDocumentTypesController(final RetrieveDocumentTypesInteractor retrieveDocumentTypesInteractor) {
        this.retrieveDocumentTypesInteractor = retrieveDocumentTypesInteractor;
    }

    @GetMapping
    public Mono<ResponseEntity<Response<CatalogItemDTO>>> retrieve() {
        return retrieveDocumentTypesInteractor.execute(null)
                .collectList()
                .map(items -> {
                    final var response = new Response<CatalogItemDTO>();
                    response.setDatos(items);
                    response.setMensajes(List.of("Tipos de documento consultados exitosamente"));
                    return ResponseEntity.ok(response);
                });
    }
}
