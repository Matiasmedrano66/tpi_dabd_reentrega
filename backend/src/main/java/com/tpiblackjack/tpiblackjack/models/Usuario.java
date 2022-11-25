package com.tpiblackjack.tpiblackjack.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "pass_wd")
    private String password;

    @OneToMany(targetEntity = Partida.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private List<Partida> partidas = new ArrayList<Partida>();

    public void agregarPartida(Partida partida)
    {
        this.partidas.add(partida);
    }
}
