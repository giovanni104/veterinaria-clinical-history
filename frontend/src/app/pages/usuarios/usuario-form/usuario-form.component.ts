import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from '../../../services/usuario.service';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.css']
})
export class UsuarioFormComponent implements OnInit {

  form!: FormGroup;
  usuarioId?: number;
  editMode = false;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      correo: ['', [Validators.required, Validators.email]],
      tipoDocumento: ['', Validators.required],
      documentoIdentificacion: ['', Validators.required],
      estado: ['', Validators.required],
      sexo: ['', Validators.required]
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.editMode = true;
      this.usuarioId = Number(id);
      this.loadUsuario(this.usuarioId);
    }
  }

  loadUsuario(id: number): void {
    this.usuarioService.getById(id).subscribe({
      next: (usuario) => this.form.patchValue(usuario),
      error: (err) => {
        console.error('Error cargando usuario', err);
        alert(err?.error?.message || 'No se pudo cargar el usuario');
      }
    });
  }

  guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const usuario = this.form.value;

    if (this.editMode && this.usuarioId) {
      this.usuarioService.update(this.usuarioId, usuario).subscribe({
        next: () => this.router.navigate(['/usuarios']),
        error: (err) => {
          console.error('Error actualizando usuario', err);
          alert(err?.error?.message || 'No se pudo actualizar el usuario');
        }
      });
      return;
    }

    this.usuarioService.create(usuario).subscribe({
      next: () => this.router.navigate(['/usuarios']),
      error: (err) => {
        console.error('Error creando usuario', err);
        alert(err?.error?.message || 'No se pudo crear el usuario');
      }
    });
  }

  volver(): void {
    this.router.navigate(['/usuarios']);
  }
}