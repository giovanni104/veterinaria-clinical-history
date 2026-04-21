import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from '../../../models/usuario.model';
import { MascotaService } from '../../../services/mascota.service';
import { UsuarioService } from '../../../services/usuario.service';

@Component({
  selector: 'app-mascota-form',
  templateUrl: './mascota-form.component.html',
  styleUrls: ['./mascota-form.component.css']
})
export class MascotaFormComponent implements OnInit {

  form!: FormGroup;
  mascotaId?: number;
  editMode = false;
  usuarios: Usuario[] = [];

  constructor(
    private fb: FormBuilder,
    private mascotaService: MascotaService,
    private usuarioService: UsuarioService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      raza: ['', Validators.required],
      vacuna: ['', Validators.required],
      sexo: ['', Validators.required],
      usuarioId: ['', Validators.required]
    });

    this.loadUsuarios();

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.editMode = true;
      this.mascotaId = Number(id);
      this.loadMascota(this.mascotaId);
    }
  }

  loadUsuarios(): void {
    this.usuarioService.getAll().subscribe({
      next: (data) => this.usuarios = data,
      error: (err) => {
        console.error('Error cargando usuarios', err);
        alert(err?.error?.message || 'No se pudieron cargar los usuarios');
      }
    });
  }

  loadMascota(id: number): void {
    this.mascotaService.getById(id).subscribe({
      next: (mascota) => {
        this.form.patchValue({
          nombre: mascota.nombre,
          raza: mascota.raza,
          vacuna: mascota.vacuna,
          sexo: mascota.sexo,
          usuarioId: mascota.usuario?.id
        });
      },
      error: (err) => {
        console.error('Error cargando mascota', err);
        alert(err?.error?.message || 'No se pudo cargar la mascota');
      }
    });
  }

  guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const mascota = this.form.value;

    if (this.editMode && this.mascotaId) {
      this.mascotaService.update(this.mascotaId, mascota).subscribe({
        next: () => this.router.navigate(['/mascotas']),
        error: (err) => {
          console.error('Error actualizando mascota', err);
          alert(err?.error?.message || 'No se pudo actualizar la mascota');
        }
      });
      return;
    }

    this.mascotaService.create(mascota).subscribe({
      next: () => this.router.navigate(['/mascotas']),
      error: (err) => {
        console.error('Error creando mascota', err);
        alert(err?.error?.message || 'No se pudo crear la mascota');
      }
    });
  }

  volver(): void {
    this.router.navigate(['/mascotas']);
  }
}