import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HeaderService } from './header.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  // private emailApi: string = "http://localhost:8080/api/v1/email";
  private emailApi: string = 'http://192.168.1.10:8080/api/v1/email';

  constructor(private http: HttpClient, private header: HeaderService) { };

  reenviarEmail(idUsuario: number): Observable<void> {
    return this.http.post<void>(`${this.emailApi}/${idUsuario}`, null, { headers: this.header.getHeader() });
  }
}
