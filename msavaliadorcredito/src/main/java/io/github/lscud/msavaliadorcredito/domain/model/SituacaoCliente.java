package io.github.lscud.msavaliadorcredito.domain.model;

import lombok.Data;

@Data
public class SituacaoCliente {

    private DadosCliente cliente;
    private List<CartaoCliente> cartoes;
}
