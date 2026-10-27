package com.dtm.reactive_application.infrastructure.primaryadapters.controller.rest.role;

import com.dtm.reactive_application.application.primaryports.dto.CatalogItemDTO;
import com.dtm.reactive_application.application.primaryports.interactor.role.RetrieveRolesInteractor;
import com.dtm.reactive_application.infrastructure.primaryadapters.controller.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RetrieveRolesController {

    private final RetrieveRolesInteractor retrieveRolesInteractor;

    public RetrieveRolesController(final RetrieveRolesInteractor retrieveRolesInteractor) {
        this.retrieveRolesInteractor = retrieveRolesInteractor;
    }

    @GetMapping
    public Mono<ResponseEntity<Response<CatalogItemDTO>>> retrieve() {
        return retrieveRolesInteractor.execute(null)
                .collectList()
                .map(items -> {
                    final var response = new Response<CatalogItemDTO>();
                    response.setDatos(items);
                    response.setMensajes(List.of("Roles consultados exitosamente"));
                    return ResponseEntity.ok(response);
                });
    }
}
