import { HttpClient, HttpResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { ILogin } from '../interfaces/auth/ILogin.interface';
import { IRegister } from '../interfaces/auth/IRegister.interface';
import { Observable } from 'rxjs';
import { IJwtToken } from '../interfaces/auth/IJwtResponse.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly http = inject(HttpClient);

  public register(register: IRegister) {
    return this.http.post(`${environment.AUTH_URL}/register`, register);
  }

  public login(login: ILogin): Observable<IJwtToken> {
    return this.http.post<IJwtToken>(`${environment.AUTH_URL}/login`, login);
  }

  public forgotPassword(forgotPassword: ILogin) {
    return this.http.post(`${environment.AUTH_URL}/forgot-password`, forgotPassword);
  }
}
