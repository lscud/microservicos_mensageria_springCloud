package io.github.lscud.msavaliadorcredito.application;

import io.github.lscud.msavaliadorcredito.domain.model.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<SituacaoCliente> consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        SituacaoCliente situacaoCliente = avaliadorCreditoSerivce.obterSituacaoCliente(cpf);
        return  ResponseEntity.ok(situacaoCliente);
    }

}
