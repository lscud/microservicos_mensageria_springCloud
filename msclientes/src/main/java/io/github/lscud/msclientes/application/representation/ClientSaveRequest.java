package io.github.lscud.msclientes.application.representation;

import io.github.lscud.msclientes.domain.Client;
import lombok.Data;

@Data
public class ClientSaveRequest {

    private String cpf;
    private String name;
    private Integer age;

    public Client toModel(){
        return new Client(cpf, name, age);
    }

}
