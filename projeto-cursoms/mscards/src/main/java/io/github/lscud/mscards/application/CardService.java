package io.github.lscud.mscards.application;

import io.github.lscud.mscards.domain.Cards;
import io.github.lscud.mscards.infra.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor // dispensa @Autowired
public class CardService {

    private final CardRepository repository;

    @Transactional
    public Cards save(Cards cards){
        return repository.save(cards);
    }

    public List<Cards> getCardsRentLessEqual(Long rent){
        var rentBigDecimal = BigDecimal.valueOf(rent);
        return repository.findByRentLessThanEqual(rentBigDecimal);
    }
}
