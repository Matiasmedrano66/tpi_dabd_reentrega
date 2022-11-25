package com.tpiblackjack.tpiblackjack.services;

import com.tpiblackjack.tpiblackjack.models.Carta;
import com.tpiblackjack.tpiblackjack.repositories.ICartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaService implements ICartaService{

    @Autowired
    private ICartaRepository _cartaRepo;

    @Override
    public Carta findCartaById(Long id) {
        return _cartaRepo.findById(id).orElse(null);
    }
}
