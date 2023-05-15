package io.github.lscud.msavaliadorcredito.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import io.github.lscud.msavaliadorcredito.domain.model.DadosSolicitacaoEmissaoCartao;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String emissaoCartoesFila;

    @Bean
    public Queue queueEmissaoCartoes(){
        return new Queue(emissaoCartoesFila,true);
    }


}
