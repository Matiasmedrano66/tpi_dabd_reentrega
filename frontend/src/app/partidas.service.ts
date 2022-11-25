import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './models/Usuario';

@Injectable({
  providedIn: 'root'
})
export class PartidasService {

 
  URI : string = "http://localhost:8080/api/partidas/"

  constructor(private cliente : HttpClient) { }

  pedirCarta(id: number):Observable<any>{
    return this.cliente.post(this.URI + "pedirCarta/", id);
  }

  nuevaPartida(id: number):Observable<any>{
    return this.cliente.post(this.URI + "new", id);
  }

  plantarse(id: number):Observable<any>{
    return this.cliente.post(this.URI + "finalizarPartida", id);
  }

}
