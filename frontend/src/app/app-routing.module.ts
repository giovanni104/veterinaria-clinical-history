import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuarioListComponent } from './pages/usuarios/usuario-list/usuario-list.component';
import { UsuarioFormComponent } from './pages/usuarios/usuario-form/usuario-form.component';
import { MascotaListComponent } from './pages/mascotas/mascota-list/mascota-list.component';
import { MascotaFormComponent } from './pages/mascotas/mascota-form/mascota-form.component';
import { ColaboradorListComponent } from './pages/colaboradores/colaborador-list/colaborador-list.component';
import { ColaboradorFormComponent } from './pages/colaboradores/colaborador-form/colaborador-form.component';
import { HistoriaClinicaListComponent } from './pages/historias-clinicas/historia-clinica-list/historia-clinica-list.component';
import { HistoriaClinicaFormComponent } from './pages/historias-clinicas/historia-clinica-form/historia-clinica-form.component';
import { DetalleHistoriaClinicaListComponent } from './pages/detalles-historia-clinica/detalle-historia-clinica-list/detalle-historia-clinica-list.component';
import { DetalleHistoriaClinicaFormComponent } from './pages/detalles-historia-clinica/detalle-historia-clinica-form/detalle-historia-clinica-form.component';

const routes: Routes = [
  { path: '', redirectTo: 'usuarios', pathMatch: 'full' },
  { path: 'usuarios', component: UsuarioListComponent },
  { path: 'usuarios/nuevo', component: UsuarioFormComponent },
  { path: 'usuarios/editar/:id', component: UsuarioFormComponent },
  { path: 'mascotas', component: MascotaListComponent },
  { path: 'mascotas/nuevo', component: MascotaFormComponent },
  { path: 'mascotas/editar/:id', component: MascotaFormComponent },
  { path: 'colaboradores', component: ColaboradorListComponent },
  { path: 'colaboradores/nuevo', component: ColaboradorFormComponent },
  { path: 'colaboradores/editar/:id', component: ColaboradorFormComponent },
  { path: 'historias', component: HistoriaClinicaListComponent },
  { path: 'historias/nuevo', component: HistoriaClinicaFormComponent },
  { path: 'historias/editar/:id', component: HistoriaClinicaFormComponent },
  { path: 'detalles', component: DetalleHistoriaClinicaListComponent },
  { path: 'detalles/nuevo', component: DetalleHistoriaClinicaFormComponent },
  { path: 'detalles/editar/:id', component: DetalleHistoriaClinicaFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }