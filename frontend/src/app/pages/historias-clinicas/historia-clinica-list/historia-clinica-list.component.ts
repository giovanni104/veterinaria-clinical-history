import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HistoriaClinica } from '../../../models/historia-clinica.model';
import { HistoriaClinicaService } from '../../../services/historia-clinica.service';

@Component({
  selector: 'app-historia-clinica-list',
  templateUrl: './historia-clinica-list.component.html'
})
export class HistoriaClinicaListComponent implements OnInit {

  historias: HistoriaClinica[] = [];
  loading = false;

  constructor(
    private historiaService: HistoriaClinicaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading = true;
    this.historiaService.getAll().subscribe({
      next: (data) => {
        this.historias = data;
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

  nuevo() {
    this.router.navigate(['/historias/nuevo']);
  }

  editar(id?: number) {
    if (id) this.router.navigate(['/historias/editar', id]);
  }

  eliminar(id?: number) {
    if (!id) return;
    if (!confirm('Eliminar historia?')) return;

    this.historiaService.delete(id).subscribe(() => this.load());
  }
}