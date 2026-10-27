package com.dtm.reactive_application.infrastructure.secondaryadapters.persistence.r2dbc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table("rol")
public class RoleEntity {

    @Id
    private UUID id;

    @Column("nombre")
    private String name;
}
