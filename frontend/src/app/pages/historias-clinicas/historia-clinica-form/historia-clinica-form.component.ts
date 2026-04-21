import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Mascota } from '../../../models/mascota.model';
import { HistoriaClinicaService } from '../../../services/historia-clinica.service';
import { MascotaService } from '../../../services/mascota.service';

@Component({
  selector: 'app-historia-clinica-form',
  templateUrl: './historia-clinica-form.component.html'
})
export class HistoriaClinicaFormComponent implements OnInit {

  form!: FormGroup;
  mascotas: Mascota[] = [];
  editMode = false;
  id?: number;

  constructor(
    private fb: FormBuilder,
    private historiaService: HistoriaClinicaService,
    private mascotaService: MascotaService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      fechaCreacion: ['', Validators.required],
      mascotaId: ['', Validators.required]
    });

    this.loadMascotas();

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.editMode = true;
      this.id = Number(id);
      this.loadHistoria(this.id);
    }
  }

  loadMascotas() {
    this.mascotaService.getAll().subscribe(data => this.mascotas = data);
  }

  loadHistoria(id: number) {
    this.historiaService.getById(id).subscribe(h => {
      this.form.patchValue({
        fechaCreacion: h.fechaCreacion,
        mascotaId: h.mascota?.id
      });
    });
  }

  guardar() {
    if (this.form.invalid) {
  this.form.markAllAsTouched();
  return;
}

    const data = this.form.value;

    if (this.editMode && this.id) {
      this.historiaService.update(this.id, data).subscribe(() => {
        this.router.navigate(['/historias']);
      });
    } else {
      this.historiaService.create(data).subscribe(() => {
        this.router.navigate(['/historias']);
      });
    }
  }

  volver() {
    this.router.navigate(['/historias']);
  }
}