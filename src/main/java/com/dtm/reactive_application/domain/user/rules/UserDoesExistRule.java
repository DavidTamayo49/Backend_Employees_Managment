package com.dtm.reactive_application.domain.user.rules;

import com.dtm.reactive_application.domain.DomainRule;

import java.util.UUID;

public interface UserDoesExistRule extends DomainRule<UUID> {
}
