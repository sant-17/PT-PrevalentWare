package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"Session\"")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Session {

    @Id
    private String id;
    private String sessionToken;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
}
