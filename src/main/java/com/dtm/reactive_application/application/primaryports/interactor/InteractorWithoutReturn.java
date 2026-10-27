package com.dtm.reactive_application.application.primaryports.interactor;

import reactor.core.publisher.Mono;

public interface InteractorWithoutReturn<T> {
    Mono<Void> execute(T data);
}
