package com.dtm.reactive_application.domain.user.rules.impl;

import com.dtm.reactive_application.crosscutting.helpers.TextHelper;
import com.dtm.reactive_application.domain.user.exceptions.UserDocumentIsNotValidException;
import com.dtm.reactive_application.domain.user.rules.UserDocumentIsValidRule;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDocumentIsValidRuleImpl implements UserDocumentIsValidRule {

    // Alfanumérico para admitir pasaportes
    private static final String DOCUMENT_PATTERN = "^[A-Za-z0-9]{5,20}$";

    @Override
    public Mono<Void> validate(final String data) {
        return TextHelper.applyTrim(data).matches(DOCUMENT_PATTERN)
                ? Mono.empty()
                : Mono.error(UserDocumentIsNotValidException.create());
    }
}
