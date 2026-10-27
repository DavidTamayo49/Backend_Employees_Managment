package com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("usuario")
public class UserEntity implements Persistable<UUID> {

    @Id
    private UUID id;

    @Column("nombre")
    private String name;

    @Column("documento")
    private String document;

    @Column("tipo_documento_id")
    private UUID documentTypeId;

    @Column("rol_id")
    private UUID roleId;

    @Column("empresa_id")
    private UUID companyId;

    // El id lo genera el dominio, así que R2DBC necesita saber cuándo es un INSERT
    @Transient
    private boolean newEntity;

    @Override
    public boolean isNew() {
        return newEntity;
    }
}
