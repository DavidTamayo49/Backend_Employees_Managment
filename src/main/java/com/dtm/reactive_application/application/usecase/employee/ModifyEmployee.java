package com.dtm.reactive_application.application.usecase.employee;

import com.dtm.reactive_application.application.usecase.UseCaseWithReturn;
import com.dtm.reactive_application.domain.user.UserDomain;
import com.dtm.reactive_application.domain.user.UserModificationDomain;

public interface ModifyEmployee extends UseCaseWithReturn<UserModificationDomain, UserDomain> {
}
