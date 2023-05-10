package io.github.lscud.msavaliadorcredito.application.ex;

public class DataClientNotFoundException extends Exception{
    public DataClientNotFoundException() {
        super("Dados do cliente nao encontrados para o CPF informado.");
    }
}
