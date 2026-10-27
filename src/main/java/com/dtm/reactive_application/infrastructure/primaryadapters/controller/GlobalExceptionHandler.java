package com.dtm.reactive_application.infrastructure.primaryadapters.controller;

import com.dtm.reactive_application.crosscutting.exception.ReactiveApplicationException;
import com.dtm.reactive_application.domain.user.exceptions.UserDoesNotExistsException;
import com.dtm.reactive_application.infrastructure.primaryadapters.controller.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserDoesNotExistsException.class)
    public Mono<ResponseEntity<Response<Object>>> handleNotFound(final UserDoesNotExistsException exception) {
        return Mono.just(build(exception.getUserMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ReactiveApplicationException.class)
    public Mono<ResponseEntity<Response<Object>>> handleControlled(final ReactiveApplicationException exception) {
        return Mono.just(build(exception.getUserMessage(), HttpStatus.BAD_REQUEST));
    }

    // Errores del propio framework (JSON mal formado, ruta inexistente...) conservan su código HTTP
    @ExceptionHandler(ResponseStatusException.class)
    public Mono<ResponseEntity<Response<Object>>> handleFramework(final ResponseStatusException exception) {
        return Mono.just(build(exception.getReason(), HttpStatus.valueOf(exception.getStatusCode().value())));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Response<Object>>> handleUnexpected(final Exception exception) {
        LOGGER.error("Error inesperado procesando la petición", exception);
        return Mono.just(build("Se ha presentado un error inesperado", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private ResponseEntity<Response<Object>> build(final String message, final HttpStatus status) {
        final var response = new Response<Object>();
        response.setMensajes(List.of(message));
        return new ResponseEntity<>(response, status);
    }
}
