package com.dtm.reactive_application.domain.user.rules;

import com.dtm.reactive_application.domain.DomainRule;
import com.dtm.reactive_application.domain.user.UserDomain;

public interface UserDocumentIsNotUsedByAnotherUserRule extends DomainRule<UserDomain> {
}
