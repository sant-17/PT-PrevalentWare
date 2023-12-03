package com.prevalentware.prueba_tecnica.domain.model;

import com.prevalentware.prueba_tecnica.domain.utils.RoleName;

import java.time.LocalDateTime;

public class RoleModel {
    private String id;
    private RoleName name;
    private LocalDateTime createdAt;

    public RoleModel(String id, RoleName name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
