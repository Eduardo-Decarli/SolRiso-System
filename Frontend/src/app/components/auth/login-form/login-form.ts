import { Component, inject, Input, OnDestroy, OnInit } from '@angular/core';
import { ILogin } from '../../../interfaces/auth/ILogin.interface';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login-form',
  imports: [FormsModule, RouterLink],
  templateUrl: './login-form.html',
  styleUrl: './login-form.scss',
  standalone: true,
})
export class LoginForm {
  private readonly loginService = inject(AuthService);

  public constructor(private router: Router) {}

  loginError: string | null = null;

  login: ILogin = {
    email: '',
    password: '',
  };

  public onSubmit(): void {
    if (this.dataValidate()) {
      this.loginService.login(this.login).subscribe({
        next: (data) => {
          localStorage.setItem('jwt', data.token);
          if (localStorage.getItem('jwt')) {
            this.router.navigate(['inicio']);
          }
        },
        error: (err) => this.showErrorMessage(err)
      });
    }
  }

  private dataValidate(): boolean {
    if (this.login.email !== '' && this.login.password !== '') {
      return true;
    }
    this.loginError = "Campos Inválidos";
    return false;
  }

  public showErrorMessage(error: HttpErrorResponse): void {
    this.loginError = error.error.message;

  }
}
