package com.dtm.reactive_application.application.usecase;

import reactor.core.publisher.Flux;

public interface UseCaseWithFluxReturn<D, R> {
    Flux<R> execute(D domain);
}
