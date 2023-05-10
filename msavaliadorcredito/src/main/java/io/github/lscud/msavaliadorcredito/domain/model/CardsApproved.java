package io.github.lscud.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardsApproved {
    private String card;
    private String flag;
    private BigDecimal limitApproved;

}
