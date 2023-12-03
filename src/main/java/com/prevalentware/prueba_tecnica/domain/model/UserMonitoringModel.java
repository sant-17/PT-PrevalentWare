package com.prevalentware.prueba_tecnica.domain.model;

import java.time.LocalDateTime;

public class UserMonitoringModel {
    private String id;
    private Integer usage;
    private String description;
    private UserModel userId;
    private LocalDateTime createdAt;
}
