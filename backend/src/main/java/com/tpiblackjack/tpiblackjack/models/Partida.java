package com.tpiblackjack.tpiblackjack.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "partidas")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fecha_partida")
    private Date fechaPartida;

    @Column(name = "puntos_usuario")
    private int puntosUsuario;

    @Column(name = "puntos_croupier")
    private int puntosCroupier;

    @OneToMany(targetEntity = DetallePartida.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_partida", referencedColumnName = "id")
    private List<DetallePartida> detallesPartida = new ArrayList<DetallePartida>();

    @Column(name = "partida_finalizada")
    private boolean partidaFinalizada;

    @Column(name = "ganador")
    private String ganador;

    public void agregarDetalle(DetallePartida detallePartida)
    {
        this.detallesPartida.add(detallePartida);
    }

}
