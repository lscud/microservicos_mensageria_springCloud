package io.github.lscud.msavaliadorcredito.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnTestClient {

    private List<CardsApproved> cards;

}
