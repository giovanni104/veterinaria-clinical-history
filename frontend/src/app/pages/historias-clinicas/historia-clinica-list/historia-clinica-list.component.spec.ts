import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoriaClinicaListComponent } from './historia-clinica-list.component';

describe('HistoriaClinicaListComponent', () => {
  let component: HistoriaClinicaListComponent;
  let fixture: ComponentFixture<HistoriaClinicaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoriaClinicaListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoriaClinicaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
