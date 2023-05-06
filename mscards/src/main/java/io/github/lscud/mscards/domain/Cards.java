package io.github.lscud.mscards.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor //controi um construtor sem argumentos
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private FlagCard flag;
    private BigDecimal rent;
    private BigDecimal limitBasic;

    public Cards(String name, FlagCard flag, BigDecimal rent, BigDecimal limitBasic) { //criado assim para auxiliar na hora de transformar a requisição em entidade
        this.name = name;
        this.flag = flag;
        this.rent = rent;
        this.limitBasic = limitBasic;
    }
}
