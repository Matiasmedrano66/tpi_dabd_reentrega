package com.tpiblackjack.tpiblackjack.services;

import com.tpiblackjack.tpiblackjack.models.CantidadPartidasGanadasDTO;
import com.tpiblackjack.tpiblackjack.models.PorcentajePartidasPuntajePerfectoDTO;
import com.tpiblackjack.tpiblackjack.models.Partida;

import java.util.List;

public interface IPartidaService {

    void savePartida(Partida partida);

    Partida findById(Long id);

    void updatePartida(Partida partida);

   List<?> traerCantidadJugadoresPorFecha();

   CantidadPartidasGanadasDTO traerCantidadPartidasGanadas();


   PorcentajePartidasPuntajePerfectoDTO traerPorcentajePartidasPuntajePerfecto();
}
