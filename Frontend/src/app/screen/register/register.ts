import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { RegisterForm } from "../../components/auth/register-form/register-form";
import { IRegister } from '../../interfaces/auth/IRegister.interface';
import { AuthService } from '../../services/auth.service';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [RegisterForm],
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {

  public constructor(private router: Router) {}
  private readonly http = inject(AuthService);

  errorMessage!: string;

  public onSubmitRegister(register: IRegister) {
    console.log(register);
    this.http.register(register).subscribe({
      next: (data) => {
        this.router.navigate(['inicio']);
      },
      error: (err) => this.errorMessage = err.error.message,
    });
  }
}
