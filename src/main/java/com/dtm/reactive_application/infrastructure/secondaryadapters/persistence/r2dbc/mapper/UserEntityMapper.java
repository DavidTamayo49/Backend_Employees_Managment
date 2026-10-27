package com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.mapper;

import com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.entity.UserEntity;
import com.dtm.reactive_application.domain.user.UserDomain;

public final class UserEntityMapper {

    private UserEntityMapper() {
    }

    public static UserEntity toNewEntity(final UserDomain domain) {
        return new UserEntity(domain.getId(), domain.getName(), domain.getDocument(),
                domain.getDocumentTypeId(), domain.getRoleId(), domain.getCompanyId(), true);
    }

    public static UserDomain toDomain(final UserEntity entity) {
        return UserDomain.create(entity.getId(), entity.getName(), entity.getDocument(),
                entity.getDocumentTypeId(), entity.getRoleId(), entity.getCompanyId());
    }
}
