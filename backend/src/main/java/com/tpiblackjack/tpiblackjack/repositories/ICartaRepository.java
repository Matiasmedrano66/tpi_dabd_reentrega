package com.tpiblackjack.tpiblackjack.repositories;

import com.tpiblackjack.tpiblackjack.models.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartaRepository extends JpaRepository<Carta, Long> {
}
