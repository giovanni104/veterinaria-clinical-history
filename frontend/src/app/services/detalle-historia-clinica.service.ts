import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DetalleHistoriaClinica } from '../models/detalle-historia-clinica.model';

@Injectable({
  providedIn: 'root'
})
export class DetalleHistoriaClinicaService {

  private apiUrl = 'http://localhost:8080/api/detalles-historia-clinica';

  constructor(private http: HttpClient) { }

  getAll(): Observable<DetalleHistoriaClinica[]> {
    return this.http.get<DetalleHistoriaClinica[]>(this.apiUrl);
  }

  getById(id: number): Observable<DetalleHistoriaClinica> {
    return this.http.get<DetalleHistoriaClinica>(`${this.apiUrl}/${id}`);
  }

  getByHistoriaClinicaId(historiaClinicaId: number): Observable<DetalleHistoriaClinica[]> {
    return this.http.get<DetalleHistoriaClinica[]>(`${this.apiUrl}/historia/${historiaClinicaId}`);
  }

  create(detalle: DetalleHistoriaClinica): Observable<DetalleHistoriaClinica> {
    return this.http.post<DetalleHistoriaClinica>(this.apiUrl, detalle);
  }

  update(id: number, detalle: DetalleHistoriaClinica): Observable<DetalleHistoriaClinica> {
    return this.http.put<DetalleHistoriaClinica>(`${this.apiUrl}/${id}`, detalle);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}