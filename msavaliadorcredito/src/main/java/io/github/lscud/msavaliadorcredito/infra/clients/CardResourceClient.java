package io.github.lscud.msavaliadorcredito.infra.clients;

import io.github.lscud.msavaliadorcredito.domain.model.Card;
import io.github.lscud.msavaliadorcredito.domain.model.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CardResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoCliente>> getCardByClient(@RequestParam("cpf") String cpf);

    @GetMapping(params = "rent")
    public ResponseEntity<List<Card>> getCardsRentUntil(@RequestParam("rent") Long rent);
}
