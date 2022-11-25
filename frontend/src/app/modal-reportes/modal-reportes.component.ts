import { Component, Input, OnInit } from '@angular/core';
import { ReporteCantidadPartidasGanadas } from '../models/ReportePartidasIndiceGanadas';

@Component({
  selector: 'app-modal-reportes',
  templateUrl: './modal-reportes.component.html',
  styleUrls: ['./modal-reportes.component.css']
})
export class ModalReportesComponent implements OnInit {

  @Input()
  reporteCantidadPartidasGanadas = {} as ReporteCantidadPartidasGanadas;

  constructor() { }

  ngOnInit(): void {
  }

}
