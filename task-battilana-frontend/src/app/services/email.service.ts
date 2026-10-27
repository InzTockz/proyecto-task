import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private emailApi: string = `${environment.apiUrl}/api/v1/email`;

  constructor(private http: HttpClient, private header: HeaderService) { };

  reenviarEmail(idUsuario: number): Observable<void> {
    return this.http.post<void>(`${this.emailApi}/${idUsuario}`, null, { headers: this.header.getHeader() });
  }
}
