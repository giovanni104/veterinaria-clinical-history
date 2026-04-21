import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Colaborador } from '../../../models/colaborador.model';
import { ColaboradorService } from '../../../services/colaborador.service';

@Component({
  selector: 'app-colaborador-list',
  templateUrl: './colaborador-list.component.html',
  styleUrls: ['./colaborador-list.component.css']
})
export class ColaboradorListComponent implements OnInit {

  colaboradores: Colaborador[] = [];
  loading = false;

  constructor(
    private colaboradorService: ColaboradorService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadColaboradores();
  }

  loadColaboradores(): void {
    this.loading = true;

    this.colaboradorService.getAll().subscribe({
      next: (data) => {
        this.colaboradores = data.sort((a, b) => (a.id ?? 0) - (b.id ?? 0));
        this.loading = false;
      },
      error: (err) => {
        console.error('Error cargando colaboradores', err);
        this.loading = false;
        alert(err?.error?.message || 'No se pudieron cargar los colaboradores');
      }
    });
  }

  nuevo(): void {
    this.router.navigate(['/colaboradores/nuevo']);
  }

  editar(id?: number): void {
    if (id) {
      this.router.navigate(['/colaboradores/editar', id]);
    }
  }

  eliminar(id?: number): void {
    if (!id) {
      return;
    }

    const confirmar = confirm(`¿Deseas eliminar el colaborador con id ${id}?`);
    if (!confirmar) {
      return;
    }

    this.colaboradorService.delete(id).subscribe({
      next: () => this.loadColaboradores(),
      error: (err) => {
        console.error('Error eliminando colaborador', err);
        alert(err?.error?.message || 'No se pudo eliminar el colaborador');
      }
    });
  }
}