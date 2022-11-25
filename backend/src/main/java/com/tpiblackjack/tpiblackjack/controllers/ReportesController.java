package com.tpiblackjack.tpiblackjack.controllers;

import com.tpiblackjack.tpiblackjack.models.CantidadPartidasGanadasDTO;
import com.tpiblackjack.tpiblackjack.models.PorcentajePartidasPuntajePerfectoDTO;
import com.tpiblackjack.tpiblackjack.services.IPartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reportes")
public class ReportesController {
  @Autowired
  private IPartidaService _partidaService;

  @GetMapping(path = "/cantidadPorFecha")
  @ResponseBody
  private ResponseEntity<?>  traerCantidadJugadoresPorFecha()
  {
    var resultado = _partidaService.traerCantidadJugadoresPorFecha();

    return ResponseEntity.ok(resultado);
  }

  @GetMapping(path = "/cantidadPartidasGanadasJugadoresYCroupier")
  @ResponseBody
  private ResponseEntity<CantidadPartidasGanadasDTO>  traerCantidadPartidasGanadas()
  {
    var resultado = _partidaService.traerCantidadPartidasGanadas();

    return ResponseEntity.ok(resultado);
  }

  @GetMapping(path = "/porcentajePartidasPuntajePerfecto")
  @ResponseBody
  private ResponseEntity<PorcentajePartidasPuntajePerfectoDTO>  traerCantidadPartidasPuntajePerfecto()
  {
    var resultado = _partidaService.traerPorcentajePartidasPuntajePerfecto();

    return ResponseEntity.ok(resultado);
  }
}
