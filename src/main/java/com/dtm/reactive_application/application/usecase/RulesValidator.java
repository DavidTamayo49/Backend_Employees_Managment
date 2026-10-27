package com.dtm.reactive_application.application.usecase;

import reactor.core.publisher.Mono;

public interface RulesValidator<D> {
    Mono<Void> validate(D data);
}
