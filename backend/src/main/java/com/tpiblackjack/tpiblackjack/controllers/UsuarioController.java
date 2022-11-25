package com.tpiblackjack.tpiblackjack.controllers;

import com.tpiblackjack.tpiblackjack.models.Partida;
import com.tpiblackjack.tpiblackjack.models.Usuario;
import com.tpiblackjack.tpiblackjack.models.UsuarioDto;
import com.tpiblackjack.tpiblackjack.repositories.IUsuarioRepository;
import com.tpiblackjack.tpiblackjack.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {
    @Autowired
    private IUsuarioService _usuarioService;

    //@GetMapping(path = ("/{username}"))
   // public Usuario findUsuarioByUsername(@PathVariable("username") String username){
     //   return _usuarioService.findUsuarioByNombreUsuario(username);
    //}

    @GetMapping(path= ("/findById/{id}"))
    public Usuario findUsuarioById(@PathVariable("id") Long id){
        return _usuarioService.findUsuarioById(id);
    }

    @PostMapping(path= "/validarUsuario")
    @ResponseBody
    public ResponseEntity<?> validarUsuario(@RequestBody Usuario usuario)
    {
        var result = _usuarioService.findUsuarioByNombreUsuario(usuario.getNombreUsuario());

        if(result != null)
        {
            Partida ultimaPartida = null;
            if(result.getPartidas().size() > 0)
            {
                ultimaPartida = result.getPartidas().get(result.getPartidas().size() - 1);
            }

            if(result.getPassword().equals(usuario.getPassword()))
            {
                var nuevoUsuario = new UsuarioDto(result.getId(), result.getNombreUsuario(), ultimaPartida);
                return ResponseEntity.ok(nuevoUsuario);
            }

            else
                return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Los datos ingresados no son correctos");
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

}
