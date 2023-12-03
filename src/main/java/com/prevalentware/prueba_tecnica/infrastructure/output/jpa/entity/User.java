package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity;

import com.prevalentware.prueba_tecnica.domain.model.CountryModel;
import com.prevalentware.prueba_tecnica.domain.model.RoleModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "\"User\"")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private String id;
    private String email;
    private LocalDateTime emailVerified;
    private LocalDateTime termsAndConditionsAccepted;
    private String name;
    private String image;
    private String position;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role roleId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "_CountryToUser",
            joinColumns = @JoinColumn(name = "B"),
            inverseJoinColumns = @JoinColumn(name = "A")
    )
    private Set<Country> countries;
}
