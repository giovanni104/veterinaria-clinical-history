import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ColaboradorService } from '../../../services/colaborador.service';

@Component({
  selector: 'app-colaborador-form',
  templateUrl: './colaborador-form.component.html',
  styleUrls: ['./colaborador-form.component.css']
})
export class ColaboradorFormComponent implements OnInit {

  form!: FormGroup;
  colaboradorId?: number;
  editMode = false;

  constructor(
    private fb: FormBuilder,
    private colaboradorService: ColaboradorService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      cargo: ['', Validators.required],
      especialidad: ['', Validators.required],
      tipoDocumento: ['', Validators.required],
      documentoIdentificacion: ['', Validators.required]
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.editMode = true;
      this.colaboradorId = Number(id);
      this.loadColaborador(this.colaboradorId);
    }
  }

  loadColaborador(id: number): void {
    this.colaboradorService.getById(id).subscribe({
      next: (colaborador) => this.form.patchValue(colaborador),
      error: (err) => {
        console.error('Error cargando colaborador', err);
        alert(err?.error?.message || 'No se pudo cargar el colaborador');
      }
    });
  }

  guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const colaborador = this.form.value;

    if (this.editMode && this.colaboradorId) {
      this.colaboradorService.update(this.colaboradorId, colaborador).subscribe({
        next: () => this.router.navigate(['/colaboradores']),
        error: (err) => {
          console.error('Error actualizando colaborador', err);
          alert(err?.error?.message || 'No se pudo actualizar el colaborador');
        }
      });
      return;
    }

    this.colaboradorService.create(colaborador).subscribe({
      next: () => this.router.navigate(['/colaboradores']),
      error: (err) => {
        console.error('Error creando colaborador', err);
        alert(err?.error?.message || 'No se pudo crear el colaborador');
      }
    });
  }

  volver(): void {
    this.router.navigate(['/colaboradores']);
  }
}