package io.github.lscud.mscards.application.representation;

import io.github.lscud.mscards.domain.Cards;
import io.github.lscud.mscards.domain.FlagCard;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSaveRequest {
    private String name;
    private FlagCard flag;
    private BigDecimal rent;
    private BigDecimal limite;

    public Cards toModel(){
        return new Cards(name, flag, rent, limite);
    }
}
