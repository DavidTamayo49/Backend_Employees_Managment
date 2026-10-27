package com.dtm.reactive_application.infrastructure.primaryadapters.controller.rest.company;

import com.dtm.reactive_application.application.primaryports.dto.CompanyDTO;
import com.dtm.reactive_application.application.primaryports.interactor.company.RetrieveCompaniesInteractor;
import com.dtm.reactive_application.infrastructure.primaryadapters.controller.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class RetrieveCompaniesController {

    private final RetrieveCompaniesInteractor retrieveCompaniesInteractor;

    public RetrieveCompaniesController(final RetrieveCompaniesInteractor retrieveCompaniesInteractor) {
        this.retrieveCompaniesInteractor = retrieveCompaniesInteractor;
    }

    @GetMapping
    public Mono<ResponseEntity<Response<CompanyDTO>>> retrieve() {
        return retrieveCompaniesInteractor.execute(null)
                .collectList()
                .map(items -> {
                    final var response = new Response<CompanyDTO>();
                    response.setDatos(items);
                    response.setMensajes(List.of("Empresas consultadas exitosamente"));
                    return ResponseEntity.ok(response);
                });
    }
}
