package com.tpiblackjack.tpiblackjack.services;

import com.tpiblackjack.tpiblackjack.models.CantidadPartidasGanadasDTO;
import com.tpiblackjack.tpiblackjack.models.PorcentajePartidasPuntajePerfectoDTO;
import com.tpiblackjack.tpiblackjack.models.Partida;
import com.tpiblackjack.tpiblackjack.repositories.IPartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidaService implements IPartidaService{

    @Autowired
    private IPartidaRepository _partidaRepo;


    @Override
    public void savePartida(Partida partida) {
        _partidaRepo.save(partida);
    }

    @Override
    public Partida findById(Long id) {
        return _partidaRepo.findById(id).orElse(null);
    }

    @Override
    public void updatePartida(Partida partida) {
        _partidaRepo.save(partida);
    }

  @Override
  public List<?> traerCantidadJugadoresPorFecha() {
    return _partidaRepo.traerCantidadJugadoresPorFecha();
  }

    @Override
    public CantidadPartidasGanadasDTO traerCantidadPartidasGanadas() {
        var cantidadPartidasGanadas = new CantidadPartidasGanadasDTO();
        cantidadPartidasGanadas.setCantidadPartidasGanadasCroupier(_partidaRepo.traerCantidadPartidasGanadasCroupier());
        cantidadPartidasGanadas.setCantidadPartidasGanadasJugadores(_partidaRepo.traerCantidadPartidasGanadasJugadores());
        return cantidadPartidasGanadas;
    }

    @Override
    public PorcentajePartidasPuntajePerfectoDTO traerPorcentajePartidasPuntajePerfecto() {
        var cantidadPartidasPuntajePerfecto = new PorcentajePartidasPuntajePerfectoDTO();
        int cantidadPartidasCroupier = _partidaRepo.traerCantidadJugadasPuntajePerfectoCroupier();
        int cantidadPartidasJugadores = _partidaRepo.traerCantidadJugadasPuntajePerfectoJugador();
        int totalPartidas = cantidadPartidasCroupier + cantidadPartidasJugadores;

        double porcentajePartidasCroupier = (cantidadPartidasCroupier * 100) / totalPartidas;
        double porcentajePartidasJugadores = (cantidadPartidasJugadores * 100) / totalPartidas;

        cantidadPartidasPuntajePerfecto.setPorcentajePartidasPuntajePerfectoCroupier(porcentajePartidasCroupier);
        cantidadPartidasPuntajePerfecto.setPorcentajePartidasPuntajePerfectoJugador(porcentajePartidasJugadores);
        return cantidadPartidasPuntajePerfecto;
    }


}
