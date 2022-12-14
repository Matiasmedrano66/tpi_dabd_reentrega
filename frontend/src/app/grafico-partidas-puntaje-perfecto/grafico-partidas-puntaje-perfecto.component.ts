import { Component, OnInit } from '@angular/core';
import { ChartData } from 'chart.js';
import { ReportePartidasPuntajePerfecto } from '../models/ReportePartidasPuntajePerfecto';
import { ReportesService } from '../reportes.service';

@Component({
  selector: 'app-grafico-partidas-puntaje-perfecto',
  templateUrl: './grafico-partidas-puntaje-perfecto.component.html',
  styleUrls: ['./grafico-partidas-puntaje-perfecto.component.css']
})
export class GraficoPartidasPuntajePerfectoComponent implements OnInit {

  reporte = {} as ReportePartidasPuntajePerfecto;
  dataset : number[] = []

  constructor(private servicioReportes : ReportesService) { }

  datos: ChartData<"pie"> = {datasets: []}


  ngOnInit(): void {
   
  }

  verReportes(){
    
    this.servicioReportes.traerprocentajePartidasPuntajePerfecto().subscribe({
      next: data => {
          this.dataset = [];
         this.reporte = data; 
          this.dataset.push(this.reporte.porcentajePartidasPuntajePerfectoCroupier)
          this.dataset.push(this.reporte.porcentajePartidasPuntajePerfectoJugador);
          this.datos = {
            labels: ["Porcentaje blackjack obtenidos por Croupier", "Porcentaje blackjack obtenidos por Jugadores"],
            datasets: [{
              data: this.dataset
            }]
          }
      },
      error: err => {
        console.log(err);
      }
     })

    
  }
  
}
