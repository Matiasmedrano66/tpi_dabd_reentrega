package com.tpiblackjack.tpiblackjack.controllers;

import com.tpiblackjack.tpiblackjack.models.DetallePartida;
import com.tpiblackjack.tpiblackjack.models.Partida;
import com.tpiblackjack.tpiblackjack.models.Usuario;
import com.tpiblackjack.tpiblackjack.services.ICartaService;
import com.tpiblackjack.tpiblackjack.services.IPartidaService;
import com.tpiblackjack.tpiblackjack.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/partidas")
public class PartidaController {

    @Autowired
    private IPartidaService _partidaService;
    @Autowired
    private IUsuarioService _usuarioService;
    @Autowired
    private ICartaService _cartaService;


    @PostMapping(path = "/save")
    public void savePartida(@RequestBody Partida partida)
    {
        _partidaService.savePartida(partida);
    }

    @PostMapping(path = "/new")
    @ResponseBody
    public Partida createPartida(@RequestBody int id)
    {
        var usuario = _usuarioService.findUsuarioById((long)id);

        //Crea nueva partida
        var partida = new Partida();

        //Guarda la fecha de la partida
        partida.setFechaPartida(new Date());

        //Elige carta jugador la busca en la base de datos y la agrega como detalle a la partida
        var idsCartasEnJuego = new Long[1];
        idsCartasEnJuego[0] = (long)0;

        Long idCartaJugador =  elegirIdCartaRandom(idsCartasEnJuego);

        var cartaJugador = _cartaService.findCartaById((long)idCartaJugador);
        var detalleUno = new DetallePartida();
        detalleUno.setCarta(cartaJugador);
        detalleUno.setEsCartaJugador(true);

        partida.agregarDetalle(detalleUno);

        //Elige carta croupier la busca en la base de datos y la agrega como detalle a la partida
        idsCartasEnJuego = obtenerIdsCartasEnJuego(partida);

        Long idCartaCroupier =  elegirIdCartaRandom(idsCartasEnJuego);

        var cartaCroupier = _cartaService.findCartaById((long)idCartaCroupier);
        var detalleDos = new DetallePartida();
        detalleDos.setCarta(cartaCroupier);
        detalleDos.setEsCartaJugador(false);

        partida.agregarDetalle(detalleDos);

        //sumar puntajes
        sumarPuntajes(partida);

        //Agrega la partida nueva al usuario
        usuario.agregarPartida(partida);

        //Actualiza los datos del usuario en la base de datos
        _usuarioService.updateUsuario(usuario);
        var listaPartidas = _usuarioService.findUsuarioById(usuario.getId()).getPartidas();
        return listaPartidas.get(listaPartidas.size() -1);
    }

    @PostMapping(path = "/pedirCarta")
    @ResponseBody
    public Partida pedirCarta(@RequestBody Long id)
    {
       var partida = _partidaService.findById(id);

        var idsCartasEnJuego = obtenerIdsCartasEnJuego(partida);

        Long idCartaJugador =  elegirIdCartaRandom(idsCartasEnJuego);

        var cartaJugador = _cartaService.findCartaById((long)idCartaJugador);
        var detalle = new DetallePartida();
        detalle.setCarta(cartaJugador);
        detalle.setEsCartaJugador(true);

        partida.agregarDetalle(detalle);

        sumarPuntajes(partida);

        if(partida.getPuntosUsuario() >= 21)
        {
            agregarJugadaCroupier(partida);
        }

        _partidaService.updatePartida(partida);

        return partida;
    }

    @PostMapping(path = "/finalizarPartida")
    @ResponseBody
    public Partida plantarse(@RequestBody Long id)
    {
        var partida = _partidaService.findById(id);

        agregarJugadaCroupier(partida);
        return partida;
    }

    private Long elegirIdCartaRandom(Long[] ids)
    {
        var idsCartasDisponibles = new Long[52 - ids.length];

        int idCarta = 0;

        for(int i = 0; i < idsCartasDisponibles.length; i++)
        {
            idCarta++;
            for(int j = 0; j < ids.length; j++)
            {
                if(idCarta != ids[j])
                {
                    idsCartasDisponibles[i] = (long)idCarta;
                }
            }
            if(idsCartasDisponibles[i] == null)
            {
                i--;
            }
        }

        var min = Math.ceil(0);
        var max = Math.floor(idsCartasDisponibles.length);

        var numeroRandom = (int)Math.floor(Math.random() * (max - min) + min);

        Long id = idsCartasDisponibles[numeroRandom];
        return id;
    }

    private Long[] obtenerIdsCartasEnJuego(Partida partida)
    {
         var idsCartasEnJuego = new Long [partida.getDetallesPartida().size()];

         for(int i = 0; i < partida.getDetallesPartida().size(); i++)
         {
             var detalle = partida.getDetallesPartida().get(i);
             idsCartasEnJuego[i] = detalle.getCarta().getId();
         }

         return idsCartasEnJuego;
    }

    private void sumarPuntajes(Partida partida)
    {
        int puntajeUsuario = 0;
        int puntajeCroupier = 0;

        for(var detalle : partida.getDetallesPartida())
        {
            if(detalle.isEsCartaJugador())
            {
                if(detalle.getCarta().getNumero() == 1)
                {
                    if((puntajeUsuario + 11) < 21)
                        puntajeUsuario += 11;
                    else
                        puntajeUsuario += 1;
                }
                else
                    puntajeUsuario += detalle.getCarta().getValor();
            }
            else
            {
                if(detalle.getCarta().getNumero() == 1)
                {
                    if((puntajeCroupier + 11) < 21)
                        puntajeCroupier += 11;
                    else
                        puntajeCroupier += 1;
                }
                else
                    puntajeCroupier += detalle.getCarta().getValor();
            }
        }

        partida.setPuntosUsuario(puntajeUsuario);
        partida.setPuntosCroupier(puntajeCroupier);
    }

    private void agregarJugadaCroupier(Partida partida)
    {
        while(partida.getPuntosCroupier() <= 16)
        {
            var idsCartasEnJuego = obtenerIdsCartasEnJuego(partida);

            Long idCartaCroupier = elegirIdCartaRandom(idsCartasEnJuego);

            var cartaCroupier = _cartaService.findCartaById((long) idCartaCroupier);

            var detalle = new DetallePartida();
            detalle.setCarta(cartaCroupier);
            detalle.setEsCartaJugador(false);
            partida.agregarDetalle(detalle);

            sumarPuntajes(partida);
            partida.setPartidaFinalizada(true);

            elegirGanador(partida);

            _partidaService.updatePartida(partida);
        }
    }

    private void elegirGanador(Partida partida)
    {
        if(partida.getPuntosCroupier() > 21 && partida.getPuntosUsuario() > 21)
            partida.setGanador("empate");
        if(partida.getPuntosCroupier() == partida.getPuntosUsuario())
            partida.setGanador("empate");
        if(partida.getPuntosUsuario() > 21 && partida.getPuntosCroupier() < 21)
            partida.setGanador("croupier");
        if(partida.getPuntosUsuario() < 21 && partida.getPuntosCroupier() > 21)
            partida.setGanador("usuario");

        if(partida.getPuntosUsuario() <= 21 && partida.getPuntosCroupier() <= 21)
        {
            if(partida.getPuntosUsuario() < partida.getPuntosCroupier())
                partida.setGanador("croupier");
            else
                partida.setGanador("usuario");
        }
    }
}
