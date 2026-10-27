package com.dtm.reactive_application.application.usecase;

import reactor.core.publisher.Mono;

public interface UseCaseWithReturn<D, R> {
    Mono<R> execute(D domain);
}
