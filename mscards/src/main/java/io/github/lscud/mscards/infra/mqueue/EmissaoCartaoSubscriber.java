package io.github.lscud.mscards.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lscud.mscards.domain.Cards;
import io.github.lscud.mscards.domain.ClientCard;
import io.github.lscud.mscards.domain.DadosSolicitacaoEmissaoCartao;
import io.github.lscud.mscards.infra.repository.CardRepository;
import io.github.lscud.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmissaoCartaoSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}") //ficará escutando essa fila mq.queues.emissao-cartoes
    public void receberSolicitacaoEmissao(@Payload String payload){
//        System.out.println(payload);
        try {
            var mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cards cartao = cardRepository.findById(dados.getIdCartao()).orElseThrow();
            ClientCard clientCard = new ClientCard();
            clientCard.setCards(cartao);
            clientCard.setCpf(dados.getCpf());
            clientCard.setLimit(dados.getLimitApproved());

            clientCardRepository.save(clientCard);

        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
