package io.github.lscud.msavaliadorcredito.infra.clients;

import io.github.lscud.msavaliadorcredito.domain.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


//Essa classe sera responsável por fazer a comunicação SINCRONA com os microserviços
//@FeignClient(url = "http://localhost:8080/clientes", path = "/clientes")
@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DadosCliente> dataClient(@RequestParam("cpf") String cpf);

}
