import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../../../models/usuario.model';
import { UsuarioService } from '../../../services/usuario.service';

@Component({
  selector: 'app-usuario-list',
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css']
})
export class UsuarioListComponent implements OnInit {

  usuarios: Usuario[] = [];
  loading = false;

  constructor(
    private usuarioService: UsuarioService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadUsuarios();
  }

  loadUsuarios(): void {
    this.loading = true;

    this.usuarioService.getAll().subscribe({
      next: (data) => {
        this.usuarios = data.sort((a, b) => (a.id ?? 0) - (b.id ?? 0));
        this.loading = false;
      },
      error: (err) => {
        console.error('Error cargando usuarios', err);
        this.loading = false;
        alert(err?.error?.message || 'No se pudieron cargar los usuarios');
      }
    });
  }

  nuevo(): void {
    this.router.navigate(['/usuarios/nuevo']);
  }

  editar(id?: number): void {
    if (id) {
      this.router.navigate(['/usuarios/editar', id]);
    }
  }

  eliminar(id?: number): void {
    if (!id) {
      return;
    }

    const confirmar = confirm(`¿Deseas eliminar el usuario con id ${id}?`);
    if (!confirmar) {
      return;
    }

    this.usuarioService.delete(id).subscribe({
      next: () => this.loadUsuarios(),
      error: (err) => {
        console.error('Error eliminando usuario', err);
        alert(err?.error?.message || 'No se pudo eliminar el usuario');
      }
    });
  }
}