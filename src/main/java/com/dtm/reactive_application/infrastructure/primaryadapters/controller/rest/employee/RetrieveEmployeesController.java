package com.dtm.reactive_application.infrastructure.primaryadapters.controller.rest.employee;

import com.dtm.reactive_application.application.primaryports.dto.EmployeeDetailDTO;
import com.dtm.reactive_application.application.primaryports.interactor.employee.RetrieveEmployeesInteractor;
import com.dtm.reactive_application.infrastructure.primaryadapters.controller.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class RetrieveEmployeesController {

    private final RetrieveEmployeesInteractor retrieveEmployeesInteractor;

    public RetrieveEmployeesController(final RetrieveEmployeesInteractor retrieveEmployeesInteractor) {
        this.retrieveEmployeesInteractor = retrieveEmployeesInteractor;
    }

    @GetMapping
    public Mono<ResponseEntity<Response<EmployeeDetailDTO>>> retrieve(@RequestParam(required = false) final UUID companyId) {
        return retrieveEmployeesInteractor.execute(companyId)
                .collectList()
                .map(employees -> {
                    final var response = new Response<EmployeeDetailDTO>();
                    response.setDatos(employees);
                    response.setMensajes(List.of(employees.isEmpty()
                            ? "No hay empleados registrados"
                            : "Empleados consultados exitosamente"));
                    return ResponseEntity.ok(response);
                });
    }
}
