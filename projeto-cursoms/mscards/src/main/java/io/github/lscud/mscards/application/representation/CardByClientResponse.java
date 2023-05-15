package io.github.lscud.mscards.application.representation;

import io.github.lscud.mscards.domain.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardByClientResponse {

    private String name;
    private String flag;
    private BigDecimal limitAproved;

    public static CardByClientResponse fromModel(ClientCard model){
        return new CardByClientResponse(
                model.getCards().getName(),
                model.getCards().getFlag().toString(),
                model.getLimit());
    }
}
