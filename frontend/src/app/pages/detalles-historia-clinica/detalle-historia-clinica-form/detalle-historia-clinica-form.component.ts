import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Colaborador } from '../../../models/colaborador.model';
import { HistoriaClinica } from '../../../models/historia-clinica.model';
import { ColaboradorService } from '../../../services/colaborador.service';
import { DetalleHistoriaClinicaService } from '../../../services/detalle-historia-clinica.service';
import { HistoriaClinicaService } from '../../../services/historia-clinica.service';

@Component({
  selector: 'app-detalle-historia-clinica-form',
  templateUrl: './detalle-historia-clinica-form.component.html',
  styleUrls: ['./detalle-historia-clinica-form.component.css']
})
export class DetalleHistoriaClinicaFormComponent implements OnInit {

  form!: FormGroup;
  detalleId?: number;
  editMode = false;
  historias: HistoriaClinica[] = [];
  colaboradores: Colaborador[] = [];

  constructor(
    private fb: FormBuilder,
    private detalleService: DetalleHistoriaClinicaService,
    private historiaService: HistoriaClinicaService,
    private colaboradorService: ColaboradorService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      temperatura: ['', [Validators.required, Validators.min(0.1)]],
      peso: ['', [Validators.required, Validators.min(0.1)]],
      frecuenciaCardiaca: ['', [Validators.required, Validators.min(0.1)]],
      frecuenciaRespiratoria: ['', [Validators.required, Validators.min(0.1)]],
      fechaHora: ['', Validators.required],
      alimentacion: ['', Validators.required],
      hidratacion: ['', Validators.required],
      observacion: ['', Validators.required],
      historiaClinicaId: ['', Validators.required],
      colaboradorId: ['', Validators.required]
    });

    this.loadHistorias();
    this.loadColaboradores();

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.editMode = true;
      this.detalleId = Number(id);
      this.loadDetalle(this.detalleId);
    }
  }

  loadHistorias(): void {
    this.historiaService.getAll().subscribe({
      next: (data) => this.historias = data,
      error: (err) => {
        console.error('Error cargando historias clínicas', err);
        alert(err?.error?.message || 'No se pudieron cargar las historias clínicas');
      }
    });
  }

  loadColaboradores(): void {
    this.colaboradorService.getAll().subscribe({
      next: (data) => this.colaboradores = data,
      error: (err) => {
        console.error('Error cargando colaboradores', err);
        alert(err?.error?.message || 'No se pudieron cargar los colaboradores');
      }
    });
  }

  loadDetalle(id: number): void {
    this.detalleService.getById(id).subscribe({
      next: (detalle) => {
        this.form.patchValue({
          temperatura: detalle.temperatura,
          peso: detalle.peso,
          frecuenciaCardiaca: detalle.frecuenciaCardiaca,
          frecuenciaRespiratoria: detalle.frecuenciaRespiratoria,
          fechaHora: detalle.fechaHora ? detalle.fechaHora.substring(0, 16) : '',
          alimentacion: detalle.alimentacion,
          hidratacion: detalle.hidratacion,
          observacion: detalle.observacion,
          historiaClinicaId: detalle.historiaClinica?.id,
          colaboradorId: detalle.colaborador?.id
        });
      },
      error: (err) => {
        console.error('Error cargando detalle clínico', err);
        alert(err?.error?.message || 'No se pudo cargar el detalle clínico');
      }
    });
  }

  guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const detalle = this.form.value;

    if (this.editMode && this.detalleId) {
      this.detalleService.update(this.detalleId, detalle).subscribe({
        next: () => this.router.navigate(['/detalles']),
        error: (err) => {
          console.error('Error actualizando detalle clínico', err);
          alert(err?.error?.message || 'No se pudo actualizar el detalle clínico');
        }
      });
      return;
    }

    this.detalleService.create(detalle).subscribe({
      next: () => this.router.navigate(['/detalles']),
      error: (err) => {
        console.error('Error creando detalle clínico', err);
        alert(err?.error?.message || 'No se pudo crear el detalle clínico');
      }
    });
  }

  volver(): void {
    this.router.navigate(['/detalles']);
  }
}