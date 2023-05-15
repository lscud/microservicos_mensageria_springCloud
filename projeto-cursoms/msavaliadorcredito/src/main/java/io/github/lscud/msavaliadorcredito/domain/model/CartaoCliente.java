package io.github.lscud.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoCliente {

    private String name;
    private String flag;
    private BigDecimal limitAproved;
}
