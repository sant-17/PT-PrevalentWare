package com.prevalentware.prueba_tecnica.domain.model;

import java.time.LocalDateTime;

public class SessionModel {
    private String id;
    private String sessionToken;
    private UserModel userId;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;

    public SessionModel(String id, String sessionToken, UserModel userId, LocalDateTime expiresAt, LocalDateTime createdAt) {
        this.id = id;
        this.sessionToken = sessionToken;
        this.userId = userId;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public UserModel getUserId() {
        return userId;
    }

    public void setUserId(UserModel userId) {
        this.userId = userId;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
