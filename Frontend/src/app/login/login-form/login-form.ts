import { Component, inject, Input } from '@angular/core';
import { ILogin } from '../../interfaces/auth/ILogin.interface';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login-form',
  imports: [FormsModule],
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss',
  standalone: true
})
export class LoginForm {

  private readonly loginService = inject(AuthService);

  loginError: string | null = null;

  login: ILogin = {
    email: '',
    password: ''
  };

  public onSubmit(): void {
    this.loginService.login(this.login).subscribe({
      next: data => {
        localStorage.setItem('jwt', data.token);
      },
      error: err => this.showErrorMessage(err),
    });
  }

  public showErrorMessage(error: HttpErrorResponse): void {
    this.loginError = error.error.message;
  }

}
