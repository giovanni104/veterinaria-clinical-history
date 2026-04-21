import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DetalleHistoriaClinica } from '../../../models/detalle-historia-clinica.model';
import { DetalleHistoriaClinicaService } from '../../../services/detalle-historia-clinica.service';

@Component({
  selector: 'app-detalle-historia-clinica-list',
  templateUrl: './detalle-historia-clinica-list.component.html',
  styleUrls: ['./detalle-historia-clinica-list.component.css']
})
export class DetalleHistoriaClinicaListComponent implements OnInit {

  detalles: DetalleHistoriaClinica[] = [];
  loading = false;

  constructor(
    private detalleService: DetalleHistoriaClinicaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadDetalles();
  }

  loadDetalles(): void {
    this.loading = true;

    this.detalleService.getAll().subscribe({
      next: (data) => {
        this.detalles = data.sort((a, b) => (a.id ?? 0) - (b.id ?? 0));
        this.loading = false;
      },
      error: (err) => {
        console.error('Error cargando detalles clínicos', err);
        this.loading = false;
        alert(err?.error?.message || 'No se pudieron cargar los detalles clínicos');
      }
    });
  }

  nuevo(): void {
    this.router.navigate(['/detalles/nuevo']);
  }

  editar(id?: number): void {
    if (id) {
      this.router.navigate(['/detalles/editar', id]);
    }
  }

  eliminar(id?: number): void {
    if (!id) {
      return;
    }

    const confirmar = confirm(`¿Deseas eliminar el detalle clínico con id ${id}?`);
    if (!confirmar) {
      return;
    }

    this.detalleService.delete(id).subscribe({
      next: () => this.loadDetalles(),
      error: (err) => {
        console.error('Error eliminando detalle clínico', err);
        alert(err?.error?.message || 'No se pudo eliminar el detalle clínico');
      }
    });
  }
}