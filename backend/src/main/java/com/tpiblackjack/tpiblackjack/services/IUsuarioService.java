package com.tpiblackjack.tpiblackjack.services;

import com.tpiblackjack.tpiblackjack.models.Usuario;

import java.util.Optional;

public interface IUsuarioService {
    Usuario findUsuarioById(Long id);

    Usuario findUsuarioByNombreUsuario(String nombreUsuario);

    void updateUsuario(Usuario usuario);
}
