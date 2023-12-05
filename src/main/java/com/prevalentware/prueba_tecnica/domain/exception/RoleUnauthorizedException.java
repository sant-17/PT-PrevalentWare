package com.prevalentware.prueba_tecnica.domain.exception;

public class RoleUnauthorizedException extends RuntimeException{

    public String message;

    @Override
    public String getMessage() {
        return message;
    }

    public RoleUnauthorizedException(String message){
        this.message = message;
    }
}
