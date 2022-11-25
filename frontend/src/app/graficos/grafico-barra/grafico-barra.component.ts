import { Component, OnInit } from '@angular/core';
import { ChartData } from 'chart.js';
import { ReportesService } from 'src/app/reportes.service';

@Component({
  selector: 'app-grafico-barra',
  templateUrl: './grafico-barra.component.html',
  styleUrls: ['./grafico-barra.component.css']
})
export class GraficoBarraComponent implements OnInit {

  cantidadJugadoresPorDia : any = []
  labelsGrafico : any = []
  datos: ChartData<"pie"> = {datasets : []} 
  constructor(private servicioReportes : ReportesService) { }

 

  verReportes(){
    
    this.servicioReportes.traerCantidadJugadoresPorfecha().subscribe({
      next: data => {
        this.labelsGrafico = [];
        this.cantidadJugadoresPorDia = [];
        for(let i = 0; i < data.length; i++){

          this.cantidadJugadoresPorDia.push(data[i][0]);
          this.labelsGrafico.push(`${new Date(data[i][1]).getUTCDate()}-${new Date(data[i][1]).toLocaleString('es-AR', {month: 'long'})}-${new Date(data[i][1]).getUTCFullYear()}` )
        }
        this.datos = {
          labels: this.labelsGrafico,
          datasets: [{
            data: this.cantidadJugadoresPorDia
          }]
        }
      }
    })

  }

  ngOnInit(): void {
   
  }

 

}
