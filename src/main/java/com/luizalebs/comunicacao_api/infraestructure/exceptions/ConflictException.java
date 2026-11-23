package com.luizalebs.comunicacao_api.infraestructure.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String messsage) {
        super(messsage);
    }

    public ConflictException(String message, Throwable throwable) {
        super(message);
    }
}
