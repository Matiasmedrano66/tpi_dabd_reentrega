package com.tpiblackjack.tpiblackjack.repositories;

import com.tpiblackjack.tpiblackjack.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

     Optional<Usuario> findUsuarioByNombreUsuario(String nombreUsuario);
}
