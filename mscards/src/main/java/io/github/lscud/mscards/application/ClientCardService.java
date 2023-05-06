package io.github.lscud.mscards.application;

import io.github.lscud.mscards.domain.ClientCard;
import io.github.lscud.mscards.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    private final ClientCardRepository repository; //como coloquei @RequiredArgsConstructor ele irá contruir construtores com argumentos obrigatorios, no caso só os que tem final
    public List<ClientCard> listCardByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
