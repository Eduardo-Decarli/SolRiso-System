import { Component, EventEmitter, Input, Output } from '@angular/core';
import { RouterLink } from "@angular/router";
import { IRegister } from '../../../interfaces/auth/IRegister.interface';
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-register-form',
  imports: [RouterLink, FormsModule],
  templateUrl: './register-form.html',
  styleUrl: './register-form.scss',
})
export class RegisterForm {

  public register: IRegister = {
    name: '',
    email: '',
    password: '',
    repassword: ''
  };

  @Input({alias: 'submitError', required: true}) sentError!: string;
  @Output('submitRegister') submitRegister = new EventEmitter<IRegister>();

  public onSubmit() {
    console.log(this.register);
    this.submitRegister.emit(this.register);
  }
}
