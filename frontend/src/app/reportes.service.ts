import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportesService {

  URI : string = "http://localhost:8080/api/reportes/"

  constructor(private cliente : HttpClient) { }

  traerCantidadJugadoresPorfecha():Observable<any>{
    return this.cliente.get(this.URI + "cantidadPorFecha");
  }

  traerCantidadParidasGanadasJugadoresYCroupier():Observable<any>{
    return this.cliente.get(this.URI + "cantidadPartidasGanadasJugadoresYCroupier");
  }
  
  traerprocentajePartidasPuntajePerfecto():Observable<any>{
    return this.cliente.get(this.URI + "porcentajePartidasPuntajePerfecto");
  }
}
