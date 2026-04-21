import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Mascota } from '../../../models/mascota.model';
import { MascotaService } from '../../../services/mascota.service';

@Component({
  selector: 'app-mascota-list',
  templateUrl: './mascota-list.component.html',
  styleUrls: ['./mascota-list.component.css']
})
export class MascotaListComponent implements OnInit {

  mascotas: Mascota[] = [];
  loading = false;

  constructor(
    private mascotaService: MascotaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadMascotas();
  }

  loadMascotas(): void {
    this.loading = true;

    this.mascotaService.getAll().subscribe({
      next: (data) => {
        this.mascotas = data.sort((a, b) => (a.id ?? 0) - (b.id ?? 0));
        this.loading = false;
      },
      error: (err) => {
        console.error('Error cargando mascotas', err);
        this.loading = false;
        alert(err?.error?.message || 'No se pudieron cargar las mascotas');
      }
    });
  }

  nuevo(): void {
    this.router.navigate(['/mascotas/nuevo']);
  }

  editar(id?: number): void {
    if (id) {
      this.router.navigate(['/mascotas/editar', id]);
    }
  }

  eliminar(id?: number): void {
    if (!id) {
      return;
    }

    const confirmar = confirm(`¿Deseas eliminar la mascota con id ${id}?`);
    if (!confirmar) {
      return;
    }

    this.mascotaService.delete(id).subscribe({
      next: () => this.loadMascotas(),
      error: (err) => {
        console.error('Error eliminando mascota', err);
        alert(err?.error?.message || 'No se pudo eliminar la mascota');
      }
    });
  }
}