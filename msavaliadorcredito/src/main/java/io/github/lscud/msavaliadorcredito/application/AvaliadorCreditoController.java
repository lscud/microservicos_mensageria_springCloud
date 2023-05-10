package io.github.lscud.msavaliadorcredito.application;

import io.github.lscud.msavaliadorcredito.application.ex.DataClientNotFoundException;
import io.github.lscud.msavaliadorcredito.application.ex.ErrorComunicationMicroservicesException;
import io.github.lscud.msavaliadorcredito.domain.model.DataTesting;
import io.github.lscud.msavaliadorcredito.domain.model.ReturnTestClient;
import io.github.lscud.msavaliadorcredito.domain.model.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {
    private final AvaliadorCreditoService avaliadorCreditoSerivce;

    @GetMapping
    public String status(){
        return "OK";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoSerivce.obterSituacaoCliente(cpf);
            return  ResponseEntity.ok(situacaoCliente);
        } catch (DataClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DataTesting dados){
        try {
            ReturnTestClient returnTestClient = avaliadorCreditoSerivce.realizarAvaliacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(returnTestClient);
        } catch (DataClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

}
