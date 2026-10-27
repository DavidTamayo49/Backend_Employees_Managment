package com.dtm.reactive_application.application.primaryports.interactor;

import reactor.core.publisher.Flux;

public interface InteractorWithFluxReturn<T, R> {
    Flux<R> execute(T data);
}
