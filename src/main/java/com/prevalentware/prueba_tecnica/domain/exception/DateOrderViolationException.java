package com.prevalentware.prueba_tecnica.domain.exception;

public class DateOrderViolationException extends RuntimeException{
    public String message;

    @Override
    public String getMessage() {
        return message;
    }

    public DateOrderViolationException(){
        this.message = "Date 'to' can't be before Date 'from";
    }
}
