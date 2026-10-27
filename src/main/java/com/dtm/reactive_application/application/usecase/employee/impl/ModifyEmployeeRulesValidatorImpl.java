package com.dtm.reactive_application.application.usecase.employee.impl;

import com.dtm.reactive_application.application.usecase.employee.ModifyEmployeeRulesValidator;
import com.dtm.reactive_application.domain.user.UserDomain;
import com.dtm.reactive_application.domain.user.rules.CompanyDoesExistRule;
import com.dtm.reactive_application.domain.user.rules.DocumentTypeDoesExistRule;
import com.dtm.reactive_application.domain.user.rules.RoleDoesExistRule;
import com.dtm.reactive_application.domain.user.rules.UserDocumentIsNotUsedByAnotherUserRule;
import com.dtm.reactive_application.domain.user.rules.UserDocumentIsValidRule;
import com.dtm.reactive_application.domain.user.rules.UserNameIsValidRule;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ModifyEmployeeRulesValidatorImpl implements ModifyEmployeeRulesValidator {

    private final UserNameIsValidRule userNameIsValidRule;
    private final UserDocumentIsValidRule userDocumentIsValidRule;
    private final DocumentTypeDoesExistRule documentTypeDoesExistRule;
    private final RoleDoesExistRule roleDoesExistRule;
    private final CompanyDoesExistRule companyDoesExistRule;
    private final UserDocumentIsNotUsedByAnotherUserRule userDocumentIsNotUsedByAnotherUserRule;

    public ModifyEmployeeRulesValidatorImpl(final UserNameIsValidRule userNameIsValidRule,
                                            final UserDocumentIsValidRule userDocumentIsValidRule,
                                            final DocumentTypeDoesExistRule documentTypeDoesExistRule,
                                            final RoleDoesExistRule roleDoesExistRule,
                                            final CompanyDoesExistRule companyDoesExistRule,
                                            final UserDocumentIsNotUsedByAnotherUserRule userDocumentIsNotUsedByAnotherUserRule) {
        this.userNameIsValidRule = userNameIsValidRule;
        this.userDocumentIsValidRule = userDocumentIsValidRule;
        this.documentTypeDoesExistRule = documentTypeDoesExistRule;
        this.roleDoesExistRule = roleDoesExistRule;
        this.companyDoesExistRule = companyDoesExistRule;
        this.userDocumentIsNotUsedByAnotherUserRule = userDocumentIsNotUsedByAnotherUserRule;
    }

    @Override
    public Mono<Void> validate(final UserDomain data) {
        // Se valida el empleado ya combinado con los cambios, con las mismas reglas que el registro
        return userNameIsValidRule.validate(data.getName())
                .then(Mono.defer(() -> userDocumentIsValidRule.validate(data.getDocument())))
                .then(Mono.defer(() -> documentTypeDoesExistRule.validate(data.getDocumentTypeId())))
                .then(Mono.defer(() -> roleDoesExistRule.validate(data.getRoleId())))
                .then(Mono.defer(() -> companyDoesExistRule.validate(data.getCompanyId())))
                .then(Mono.defer(() -> userDocumentIsNotUsedByAnotherUserRule.validate(data)));
    }
}
