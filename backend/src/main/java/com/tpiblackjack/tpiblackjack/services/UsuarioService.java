package com.tpiblackjack.tpiblackjack.services;

import com.tpiblackjack.tpiblackjack.models.Usuario;
import com.tpiblackjack.tpiblackjack.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository _usuarioRepo;

    @Override
    public Usuario findUsuarioById(Long id) {
        return _usuarioRepo.findById(id).orElse(null);
    }

    @Override
    public Usuario findUsuarioByNombreUsuario(String nombreUsuario) {
        return _usuarioRepo.findUsuarioByNombreUsuario(nombreUsuario).orElse(null);
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        _usuarioRepo.save(usuario);
    }
}
