import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './models/Usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {


  URI : string = "http://localhost:8080/api/usuarios/"

  constructor(private cliente : HttpClient) { }

  validarUsuario(usuario: Usuario):Observable<any>{
    return this.cliente.post(this.URI + "validarUsuario", usuario);
  }

}
