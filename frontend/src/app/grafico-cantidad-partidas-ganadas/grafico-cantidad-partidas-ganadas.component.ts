import { Component, OnInit } from '@angular/core';
import { ChartData } from 'chart.js';
import { ReporteCantidadPartidasGanadas } from '../models/ReportePartidasIndiceGanadas';
import { ReportesService } from '../reportes.service';

@Component({
  selector: 'app-grafico-cantidad-partidas-ganadas',
  templateUrl: './grafico-cantidad-partidas-ganadas.component.html',
  styleUrls: ['./grafico-cantidad-partidas-ganadas.component.css']
})
export class GraficoCantidadPartidasGanadasComponent implements OnInit {

  reporte = {} as ReporteCantidadPartidasGanadas;
  dataset : number[] = []

  constructor(private servicioReportes : ReportesService) { }

  datos: ChartData<"pie"> = {
    labels: ["Partidas ganadas Croupier ", "Partidas ganadas Jugadores"],
    datasets: [{
      data: this.dataset
    }]
  }


  ngOnInit(): void {
   this.servicioReportes.traerCantidadParidasGanadasJugadoresYCroupier().subscribe({
    next: data => {
        this.reporte = data;
        this.dataset.push(this.reporte.cantidadPartidasGanadasCroupier)
        this.dataset.push(this.reporte.cantidadPartidasGanadasJugadores);
    },
    error: err => {
      console.log(err);
    }
   })
  }

}
