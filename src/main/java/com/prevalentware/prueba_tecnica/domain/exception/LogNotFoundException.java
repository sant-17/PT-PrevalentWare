package com.prevalentware.prueba_tecnica.domain.exception;

public class LogNotFoundException extends RuntimeException{

     public String message;

    @Override
    public String getMessage() {
        return this.message;
    }

    public LogNotFoundException(String message){
        this.message = message;
    }
}
