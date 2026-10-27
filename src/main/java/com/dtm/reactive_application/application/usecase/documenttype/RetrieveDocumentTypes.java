package com.dtm.reactive_application.application.usecase.documenttype;

import com.dtm.reactive_application.application.usecase.UseCaseWithFluxReturn;
import com.dtm.reactive_application.domain.documenttype.DocumentTypeDomain;

public interface RetrieveDocumentTypes extends UseCaseWithFluxReturn<Void, DocumentTypeDomain> {
}
