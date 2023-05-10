package io.github.lscud.msavaliadorcredito.application;

import feign.FeignException;
import io.github.lscud.msavaliadorcredito.application.ex.DataClientNotFoundException;
import io.github.lscud.msavaliadorcredito.application.ex.ErrorComunicationMicroservicesException;
import io.github.lscud.msavaliadorcredito.domain.model.*;
import io.github.lscud.msavaliadorcredito.infra.clients.CardResourceClient;
import io.github.lscud.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesClient;
    private final CardResourceClient cardClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DataClientNotFoundException, ErrorComunicationMicroservicesException{
        try {
            //obterDadosCliente - MSCLIENTES
            //obter Cartoes do cliente = MSCartoes


            ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dataClient(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cardClient.getCardByClient(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();
        }
        catch (FeignException.FeignClientException e){
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status){
                throw new DataClientNotFoundException();
            }
            throw new ErrorComunicationMicroservicesException(e.getMessage(), status);
        }


    }

    public ReturnTestClient realizarAvaliacao(String cpf, Long renda) throws DataClientNotFoundException, ErrorComunicationMicroservicesException{
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dataClient(cpf);
            ResponseEntity<List<Card>> cardsRent = cardClient.getCardsRentUntil(renda);

            List<Card> cartoes = cardsRent.getBody();
            var listCardsApproved = cartoes.stream().map(card -> {
                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limitBasic = card.getLimitBasic();
//                BigDecimal rentBD = BigDecimal.valueOf(renda);
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                var factor = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limitApproved = factor.multiply(limitBasic);

                CardsApproved approved = new CardsApproved();
                approved.setCard(card.getName());
                approved.setFlag(card.getFlag());
                approved.setLimitApproved(limitApproved);

                return approved;
            }).collect(Collectors.toList());
            return new ReturnTestClient(listCardsApproved);
        }
        catch (FeignException.FeignClientException e){
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status){
                throw new DataClientNotFoundException();
            }
            throw new ErrorComunicationMicroservicesException(e.getMessage(), status);
        }
    }
}
