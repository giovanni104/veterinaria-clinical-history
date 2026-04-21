import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleHistoriaClinicaFormComponent } from './detalle-historia-clinica-form.component';

describe('DetalleHistoriaClinicaFormComponent', () => {
  let component: DetalleHistoriaClinicaFormComponent;
  let fixture: ComponentFixture<DetalleHistoriaClinicaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetalleHistoriaClinicaFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalleHistoriaClinicaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
