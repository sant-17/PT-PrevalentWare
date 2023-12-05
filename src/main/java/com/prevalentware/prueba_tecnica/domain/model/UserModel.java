package com.prevalentware.prueba_tecnica.domain.model;

import java.time.LocalDateTime;
import java.util.Set;

public class UserModel {
    private String id;
    private String email;
    private LocalDateTime emailVerified;
    private LocalDateTime termsAndConditionsAccepted;
    private String name;
    private String image;
    private String position;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private RoleModel roleId;
    private Set<CountryModel> countries;

    public UserModel(String id, String email, LocalDateTime emailVerified, LocalDateTime termsAndConditionsAccepted, String name, String image, String position, LocalDateTime createdAt, LocalDateTime updatedAt, RoleModel roleId, Set<CountryModel> countries) {
        this.id = id;
        this.email = email;
        this.emailVerified = emailVerified;
        this.termsAndConditionsAccepted = termsAndConditionsAccepted;
        this.name = name;
        this.image = image;
        this.position = position;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roleId = roleId;
        this.countries = countries;
    }

    public UserModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(LocalDateTime emailVerified) {
        this.emailVerified = emailVerified;
    }

    public LocalDateTime getTermsAndConditionsAccepted() {
        return termsAndConditionsAccepted;
    }

    public void setTermsAndConditionsAccepted(LocalDateTime termsAndConditionsAccepted) {
        this.termsAndConditionsAccepted = termsAndConditionsAccepted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public RoleModel getRoleId() {
        return roleId;
    }

    public void setRoleId(RoleModel roleId) {
        this.roleId = roleId;
    }

    public Set<CountryModel> getCountries() {
        return countries;
    }

    public void setCountries(Set<CountryModel> countries) {
        this.countries = countries;
    }
}
