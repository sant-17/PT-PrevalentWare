package com.prevalentware.prueba_tecnica.infrastructure.output.jpa.entity;

import com.prevalentware.prueba_tecnica.domain.model.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"UserMonitoring\"")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserMonitoring {
    @Id
    private String id;
    private Integer usage;
    private String description;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;
    private LocalDateTime createdAt;
}
