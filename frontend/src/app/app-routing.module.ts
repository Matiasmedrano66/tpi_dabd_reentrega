import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ModalReportesComponent } from './modal-reportes/modal-reportes.component';

const routes: Routes = [ {path: "reportes", component: ModalReportesComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
