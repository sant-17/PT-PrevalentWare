package com.prevalentware.prueba_tecnica.domain.model;

import java.time.LocalDateTime;

public class UserMonitoringModel {
    private String id;
    private Integer usage;
    private String description;
    private UserModel userId;
    private LocalDateTime createdAt;

    public UserMonitoringModel(String id, Integer usage, String description, UserModel userId, LocalDateTime createdAt) {
        this.id = id;
        this.usage = usage;
        this.description = description;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUsage() {
        return usage;
    }

    public void setUsage(Integer usage) {
        this.usage = usage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getUserId() {
        return userId;
    }

    public void setUserId(UserModel userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}