package io.github.lscud.msclientes.application;

import io.github.lscud.msclientes.application.representation.ClientSaveRequest;
import io.github.lscud.msclientes.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor //recurso do lombok dispensa uso do @Autowired
@Slf4j
public class ClientResource {

    private final ClientService service;

    @GetMapping
    public String status(){
        log.info("obtendo o status do microservice de clientes");
        return "OK";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClientSaveRequest request){

        Client client = request.toModel();
        service.save(client);
        URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={cpf}").buildAndExpand(client.getCpf()).toUri();  //serve para consturir urls dinamicas. .query é quando queremos passar parametros para url
        // http://localhost:PORT/clientes?cpf=0123456789 queremos que seja gerado algo assim
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dataClient(@RequestParam("cpf") String cpf){
        var client = service.getByCpf(cpf);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }
}
