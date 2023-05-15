package io.github.lscud.msclientes.application;

import io.github.lscud.msclientes.domain.Client;
import io.github.lscud.msclientes.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor //lombok para instancias o repositorio

public class ClientService {

    private final ClientRepository repository;

    public Client save(Client client){
        return repository.save(client);
    }

    public Optional<Client> getByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
