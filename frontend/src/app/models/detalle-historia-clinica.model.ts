export interface DetalleHistoriaClinica {
  id?: number;
  temperatura: number;
  peso: number;
  frecuenciaCardiaca: number;
  frecuenciaRespiratoria: number;
  fechaHora: string;
  alimentacion: string;
  hidratacion: string;
  observacion: string;
  historiaClinicaId: number;
  colaboradorId: number;
  historiaClinica?: any;
  colaborador?: any;
}