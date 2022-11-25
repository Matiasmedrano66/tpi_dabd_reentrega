import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MesaComponent } from './mesa/mesa.component';
import { CartaComponent } from './carta/carta.component';
import { JuegoComponent } from './juego/juego.component';
import { FormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import {NgChartsModule} from 'ng2-charts';
import { ModalReportesComponent } from './modal-reportes/modal-reportes.component';
import { GraficoBarraComponent } from './graficos/grafico-barra/grafico-barra.component';
import { GraficoCantidadPartidasGanadasComponent } from './grafico-cantidad-partidas-ganadas/grafico-cantidad-partidas-ganadas.component';
import { GraficoPartidasPuntajePerfectoComponent } from './grafico-partidas-puntaje-perfecto/grafico-partidas-puntaje-perfecto.component';


@NgModule({
  declarations: [
    AppComponent,
    MesaComponent,
    CartaComponent,
    JuegoComponent,
    ModalReportesComponent,
    GraficoBarraComponent,
    GraficoCantidadPartidasGanadasComponent,
    GraficoPartidasPuntajePerfectoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgChartsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
