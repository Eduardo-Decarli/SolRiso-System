import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { ILogin } from '../interfaces/auth/ILogin.interface';
import { IRegister } from '../interfaces/auth/IRegister.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private readonly HTTP = inject(HttpClient);

  private register(register: IRegister) {
    return this.HTTP.post(`${environment}/register`, register);
  }

  private login(login: ILogin) {
    return this.HTTP.post(`${environment}/login`, login);
  }

  private forgotPassword(forgotPassword: ILogin) {
    return this.HTTP.post(`${environment}/forgot-password`, forgotPassword);
  }

}
