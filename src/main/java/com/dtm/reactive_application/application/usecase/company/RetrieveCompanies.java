package com.dtm.reactive_application.application.usecase.company;

import com.dtm.reactive_application.application.usecase.UseCaseWithFluxReturn;
import com.dtm.reactive_application.domain.company.CompanyDomain;

public interface RetrieveCompanies extends UseCaseWithFluxReturn<Void, CompanyDomain> {
}
