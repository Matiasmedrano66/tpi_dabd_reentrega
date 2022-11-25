package com.tpiblackjack.tpiblackjack.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cartas")
public class Carta {

    @Id
    private Long id;

    private int numero;

    private String palo;

    private int valor;

    @Column(name = "url_imagen")
    private String urlImagen;

}
