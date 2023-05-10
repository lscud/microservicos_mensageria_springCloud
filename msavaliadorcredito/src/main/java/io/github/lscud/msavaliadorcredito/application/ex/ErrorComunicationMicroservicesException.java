package io.github.lscud.msavaliadorcredito.application.ex;

import lombok.Getter;

public class ErrorComunicationMicroservicesException extends Exception{

    @Getter
    private Integer status;
    public ErrorComunicationMicroservicesException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
