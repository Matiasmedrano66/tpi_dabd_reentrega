package com.tpiblackjack.tpiblackjack.repositories;

import com.tpiblackjack.tpiblackjack.models.CantidadPartidasGanadasDTO;
import com.tpiblackjack.tpiblackjack.models.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPartidaRepository extends JpaRepository<Partida, Long> {
  @Query(
    value = "SELECT count(distinct id_usuario), fecha_partida from partidas group by date(fecha_partida)",
    nativeQuery = true)
  List<?> traerCantidadJugadoresPorFecha();

  @Query(
          value = "Select count(id) from partidas  WHERE ganador = 'croupier'",
          nativeQuery = true)
  int traerCantidadPartidasGanadasCroupier();

  @Query(
          value = "Select count(id) from partidas  WHERE ganador = 'usuario'",
          nativeQuery = true)
  int traerCantidadPartidasGanadasJugadores();

  @Query(
          value = "Select count(id) from partidas  WHERE puntos_usuario = 21",
          nativeQuery = true)
  int traerCantidadJugadasPuntajePerfectoJugador();

  @Query(
          value = "Select count(id) from partidas  WHERE puntos_croupier = 21",
          nativeQuery = true)
  int traerCantidadJugadasPuntajePerfectoCroupier();
}
