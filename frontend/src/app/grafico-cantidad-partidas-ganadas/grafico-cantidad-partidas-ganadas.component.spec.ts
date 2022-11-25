import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraficoCantidadPartidasGanadasComponent } from './grafico-cantidad-partidas-ganadas.component';

describe('GraficoCantidadPartidasGanadasComponent', () => {
  let component: GraficoCantidadPartidasGanadasComponent;
  let fixture: ComponentFixture<GraficoCantidadPartidasGanadasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GraficoCantidadPartidasGanadasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GraficoCantidadPartidasGanadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
