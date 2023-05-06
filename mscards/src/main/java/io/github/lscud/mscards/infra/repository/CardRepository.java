package io.github.lscud.mscards.infra.repository;

import io.github.lscud.mscards.domain.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository extends JpaRepository<Cards, Long> {
    List<Cards> findByRentLessThanEqual(BigDecimal rent);
}
