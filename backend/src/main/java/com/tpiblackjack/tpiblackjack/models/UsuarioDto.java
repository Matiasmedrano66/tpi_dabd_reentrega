package com.tpiblackjack.tpiblackjack.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    private Long id;

    private String nombreUsuario;

    //private List<Partida> partidas = new ArrayList<Partida>();

    private Partida ultimaPartida;
}
