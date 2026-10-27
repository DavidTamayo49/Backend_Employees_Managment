package com.dtm.reactive_application.infrastructure.primaryadapters.controller.rest.employee;

import com.dtm.reactive_application.application.primaryports.dto.EmployeeDTO;
import com.dtm.reactive_application.application.primaryports.dto.ModifyEmployeeDTO;
import com.dtm.reactive_application.application.primaryports.interactor.employee.ModifyEmployeeInteractor;
import com.dtm.reactive_application.infrastructure.primaryadapters.controller.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class ModifyEmployeeController {

    private final ModifyEmployeeInteractor modifyEmployeeInteractor;

    public ModifyEmployeeController(final ModifyEmployeeInteractor modifyEmployeeInteractor) {
        this.modifyEmployeeInteractor = modifyEmployeeInteractor;
    }

    @PatchMapping("/{id}")
    public Mono<ResponseEntity<Response<EmployeeDTO>>> modify(@PathVariable final UUID id,
                                                              @RequestBody final ModifyEmployeeDTO dto) {
        // El id de la ruta manda sobre cualquier id que venga en el cuerpo
        return modifyEmployeeInteractor.execute(dto.withId(id))
                .map(employee -> {
                    final var response = new Response<EmployeeDTO>();
                    response.setDatos(List.of(employee));
                    response.setMensajes(List.of("Empleado modificado exitosamente"));
                    return ResponseEntity.ok(response);
                });
    }
}
