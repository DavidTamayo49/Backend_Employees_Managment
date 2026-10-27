package com.dtm.reactive_application.infrastructure.primaryadapters.controller.rest.employee;

import com.dtm.reactive_application.application.primaryports.dto.EmployeeDTO;
import com.dtm.reactive_application.application.primaryports.dto.RegisterNewEmployeeDTO;
import com.dtm.reactive_application.application.primaryports.interactor.employee.RegisterNewEmployeeInteractor;
import com.dtm.reactive_application.infrastructure.primaryadapters.controller.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class RegisterNewEmployeeController {

    private final RegisterNewEmployeeInteractor registerNewEmployeeInteractor;

    public RegisterNewEmployeeController(final RegisterNewEmployeeInteractor registerNewEmployeeInteractor) {
        this.registerNewEmployeeInteractor = registerNewEmployeeInteractor;
    }

    @PostMapping
    public Mono<ResponseEntity<Response<EmployeeDTO>>> register(@RequestBody final RegisterNewEmployeeDTO dto) {
        return registerNewEmployeeInteractor.execute(dto)
                .map(employee -> {
                    final var response = new Response<EmployeeDTO>();
                    response.setDatos(List.of(employee));
                    response.setMensajes(List.of("Empleado registrado exitosamente"));
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                });
    }
}
