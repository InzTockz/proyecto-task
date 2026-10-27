import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsuariosResponse } from '../models/usuarios/usuarios-response';
import { Observable, ObservableLike } from 'rxjs';
import { UsuariosRequest } from '../models/usuarios/usuarios-request';
import { HeaderService } from './header.service';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private usuarioApi: string = `${environment.apiUrl}/v1/api/usuario`;

  constructor(private http: HttpClient, private header: HeaderService) { }

  getUsuarios(): Observable<UsuariosResponse[]> {
    return this.http.get<UsuariosResponse[]>(`${this.usuarioApi}/listar`, { headers: this.header.getHeader() });
  }

  getUsuarioId(idUsuario: number): Observable<UsuariosResponse> {
    return this.http.get<UsuariosResponse>(`${this.usuarioApi}/listar/${idUsuario}`, { headers: this.header.getHeader() });
  }

  postUsuarios(usuario: UsuariosRequest): Observable<UsuariosResponse> {
    return this.http.post<UsuariosResponse>(`${this.usuarioApi}/registrar`, usuario, { headers: this.header.getHeader() })
  }

  putUsuarios(idUsuario: number, usuario: UsuariosRequest): Observable<UsuariosResponse> {
    return this.http.put<UsuariosResponse>(`${this.usuarioApi}/actualizar/${idUsuario}`, usuario, { headers: this.header.getHeader() });
  }

  deleteUsuarios(idUsuario: number): Observable<void> {
    return this.http.delete<void>(`${this.usuarioApi}/eliminar/${idUsuario}`, { headers: this.header.getHeader() });
  }
}
