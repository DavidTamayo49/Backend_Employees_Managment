package com.dtm.reactive_application.domain.user.rules.impl;

import com.dtm.reactive_application.crosscutting.helpers.TextHelper;
import com.dtm.reactive_application.domain.user.exceptions.UserNameIsNotValidException;
import com.dtm.reactive_application.domain.user.rules.UserNameIsValidRule;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserNameIsValidRuleImpl implements UserNameIsValidRule {

    private static final int MAX_LENGTH = 100;

    @Override
    public Mono<Void> validate(final String data) {
        final var name = TextHelper.applyTrim(data);
        return name.isEmpty() || name.length() > MAX_LENGTH
                ? Mono.error(UserNameIsNotValidException.create())
                : Mono.empty();
    }
}
