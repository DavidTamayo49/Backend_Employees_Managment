package com.dtm.reactive_application.application.usecase;

import reactor.core.publisher.Mono;

public interface UseCaseWithoutReturn<D> {
    Mono<Void> execute(D domain);
}
