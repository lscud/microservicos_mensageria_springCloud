package io.github.lscud.mscards.application;

import io.github.lscud.mscards.application.representation.CardByClientResponse;
import io.github.lscud.mscards.application.representation.CardSaveRequest;
import io.github.lscud.mscards.domain.Cards;
import io.github.lscud.mscards.domain.ClientCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CardsResource {


    private final CardService cardService;

    private final ClientCardService clientCardService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity cadastra(@RequestBody CardSaveRequest request){
        Cards cards = request.toModel();
        cardService.save(cards);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //se eu passar /cartoes?rent=5000 por exemplo ele cairá nesse endpoint se não se for só /cartoes irá no primeiro @Getmapping
    @GetMapping(params = "rent")
    public ResponseEntity<List<Cards>> getCardsRentUntil(@RequestParam("rent") Long rent){
        List<Cards> list = cardService.getCardsRentLessEqual(rent);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardByClientResponse>> getCardByClient(@RequestParam("cpf") String cpf){
        List<ClientCard> list = clientCardService.listCardByCpf(cpf);
        List<CardByClientResponse> resultlist = list.stream().map(CardByClientResponse::fromModel).collect(Collectors.toList());
        return ResponseEntity.ok(resultlist);
    }


}
