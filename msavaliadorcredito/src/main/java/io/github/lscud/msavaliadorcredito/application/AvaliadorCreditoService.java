package io.github.lscud.msavaliadorcredito.application;

import io.github.lscud.msavaliadorcredito.domain.model.DadosCliente;
import io.github.lscud.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.lscud.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesClient;

    public SituacaoCliente obterSituacaoCliente(String cpf){
        //obterDadosCliente - MSCLIENTES
        //obter Cartoes do cliente = MSCartoes


        ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dataClient(cpf);
        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .build();


    }
}
