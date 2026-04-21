import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleHistoriaClinicaListComponent } from './detalle-historia-clinica-list.component';

describe('DetalleHistoriaClinicaListComponent', () => {
  let component: DetalleHistoriaClinicaListComponent;
  let fixture: ComponentFixture<DetalleHistoriaClinicaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetalleHistoriaClinicaListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalleHistoriaClinicaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
