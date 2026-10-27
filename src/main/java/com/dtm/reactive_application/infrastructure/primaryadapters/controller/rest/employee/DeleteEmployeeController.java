package com.dtm.reactive_application.infrastructure.primaryadapters.controller.rest.employee;

import com.dtm.reactive_application.application.primaryports.interactor.employee.DeleteEmployeeInteractor;
import com.dtm.reactive_application.infrastructure.primaryadapters.controller.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class DeleteEmployeeController {

    private final DeleteEmployeeInteractor deleteEmployeeInteractor;

    public DeleteEmployeeController(final DeleteEmployeeInteractor deleteEmployeeInteractor) {
        this.deleteEmployeeInteractor = deleteEmployeeInteractor;
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Response<Object>>> delete(@PathVariable final UUID id) {
        return deleteEmployeeInteractor.execute(id)
                .then(Mono.fromSupplier(() -> {
                    final var response = new Response<Object>();
                    response.setMensajes(List.of("Empleado eliminado exitosamente"));
                    return ResponseEntity.ok(response);
                }));
    }
}
