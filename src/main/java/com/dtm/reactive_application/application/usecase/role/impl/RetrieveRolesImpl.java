package com.dtm.reactive_application.application.usecase.role.impl;

import com.dtm.reactive_application.application.secondaryports.repository.RoleRepository;
import com.dtm.reactive_application.application.usecase.role.RetrieveRoles;
import com.dtm.reactive_application.domain.role.RoleDomain;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RetrieveRolesImpl implements RetrieveRoles {

    private final RoleRepository repository;

    public RetrieveRolesImpl(final RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<RoleDomain> execute(final Void domain) {
        return repository.findAll();
    }
}
