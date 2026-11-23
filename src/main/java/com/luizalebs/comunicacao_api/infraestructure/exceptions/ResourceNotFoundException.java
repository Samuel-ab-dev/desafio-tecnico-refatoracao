package com.luizalebs.comunicacao_api.infraestructure.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String messsage) {
        super(messsage);
    }

    public ResourceNotFoundException(String message, Throwable throwable) {
        super(message);
    }
}
