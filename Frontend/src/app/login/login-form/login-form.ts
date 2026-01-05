import { Component, inject, Input } from '@angular/core';
import { ILogin } from '../../interfaces/auth/ILogin.interface';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login-form',
  imports: [FormsModule],
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss',
})
export class LoginForm {

  private readonly loginService = inject(AuthService)

  teste: string = ''

  login: ILogin = {
    email: '',
    password: ''
  };

  onSubmit(): void {

  }

}
