package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity;

import com.prevalentware.prueba_tecnica.domain.utils.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"Role\"")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private RoleName name;
    private LocalDateTime createdAt;
}