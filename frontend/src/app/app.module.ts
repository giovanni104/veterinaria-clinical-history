import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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





@NgModule({
  declarations: [
    AppComponent,
    UsuarioListComponent,
    UsuarioFormComponent,
    MascotaListComponent,
    MascotaFormComponent,
    ColaboradorListComponent,
    ColaboradorFormComponent,
    HistoriaClinicaListComponent,
    HistoriaClinicaFormComponent,
    DetalleHistoriaClinicaListComponent,
    DetalleHistoriaClinicaFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }