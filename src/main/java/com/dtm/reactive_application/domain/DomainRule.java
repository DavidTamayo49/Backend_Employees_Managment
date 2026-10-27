package com.dtm.reactive_application.domain;

import reactor.core.publisher.Mono;

public interface DomainRule<T> {
    Mono<Void> validate(T data);
}
