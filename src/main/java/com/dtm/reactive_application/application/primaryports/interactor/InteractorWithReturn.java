package com.dtm.reactive_application.application.primaryports.interactor;

import reactor.core.publisher.Mono;

public interface InteractorWithReturn<T, R> {
    Mono<R> execute(T data);
}
