import { Component } from '@angular/core';
import { LoginForm } from '../../components/auth/login-form/login-form';
import { LoginHeader } from '../../components/auth/login-header/login-header';

@Component({
  selector: 'app-login',
  imports: [LoginForm, LoginHeader],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

}
