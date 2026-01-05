import { Component } from '@angular/core';
import { LoginForm } from './login-form/login-form';
import { LoginHeader } from './login-header/login-header';

@Component({
  selector: 'app-login',
  imports: [LoginForm, LoginHeader],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

}
