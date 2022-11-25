import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraficoPartidasPuntajePerfectoComponent } from './grafico-partidas-puntaje-perfecto.component';

describe('GraficoPartidasPuntajePerfectoComponent', () => {
  let component: GraficoPartidasPuntajePerfectoComponent;
  let fixture: ComponentFixture<GraficoPartidasPuntajePerfectoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GraficoPartidasPuntajePerfectoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GraficoPartidasPuntajePerfectoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
