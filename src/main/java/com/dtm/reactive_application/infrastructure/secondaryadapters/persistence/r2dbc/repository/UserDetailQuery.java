package com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.repository;

import com.dtm.reactive_application.domain.company.CompanyDomain;
import com.dtm.reactive_application.domain.documenttype.DocumentTypeDomain;
import com.dtm.reactive_application.domain.role.RoleDomain;
import com.dtm.reactive_application.domain.user.UserDetailDomain;
import io.r2dbc.spi.Readable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

// Consulta de lectura con JOIN: trae cada empleado con su tipo de documento, rol y empresa en una sola ida a la BD
@Component
public class UserDetailQuery {

    private static final String SELECT_DETAIL = """
            SELECT u.id, u.nombre, u.documento,
                   td.id AS tipo_documento_id, td.nombre AS tipo_documento_nombre,
                   r.id  AS rol_id,            r.nombre  AS rol_nombre,
                   e.id  AS empresa_id,        e.nombre  AS empresa_nombre, e.nit AS empresa_nit
            FROM usuario u
            JOIN tipo_documento td ON td.id = u.tipo_documento_id
            JOIN rol r             ON r.id  = u.rol_id
            JOIN empresa e         ON e.id  = u.empresa_id
            """;

    private static final String ORDER_BY = " ORDER BY u.nombre";

    private final DatabaseClient databaseClient;

    public UserDetailQuery(final DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public Flux<UserDetailDomain> findAll() {
        return databaseClient.sql(SELECT_DETAIL + ORDER_BY)
                .map(UserDetailQuery::toDomain)
                .all();
    }

    public Flux<UserDetailDomain> findByCompanyId(final UUID companyId) {
        return databaseClient.sql(SELECT_DETAIL + " WHERE u.empresa_id = :companyId" + ORDER_BY)
                .bind("companyId", companyId)
                .map(UserDetailQuery::toDomain)
                .all();
    }

    private static UserDetailDomain toDomain(final Readable row) {
        return UserDetailDomain.create(
                row.get("id", UUID.class),
                row.get("nombre", String.class),
                row.get("documento", String.class),
                DocumentTypeDomain.create(row.get("tipo_documento_id", UUID.class), row.get("tipo_documento_nombre", String.class)),
                RoleDomain.create(row.get("rol_id", UUID.class), row.get("rol_nombre", String.class)),
                CompanyDomain.create(row.get("empresa_id", UUID.class), row.get("empresa_nombre", String.class),
                        row.get("empresa_nit", String.class)));
    }
}
