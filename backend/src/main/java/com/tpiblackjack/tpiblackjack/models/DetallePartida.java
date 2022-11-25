package com.tpiblackjack.tpiblackjack.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "detalles_partida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePartida {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "es_carta_jugador")
    private boolean esCartaJugador;

    @ManyToOne
    @JoinColumn(name = "id_carta",referencedColumnName = "id")
    private Carta carta;
}
