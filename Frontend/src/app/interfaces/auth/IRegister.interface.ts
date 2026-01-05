export interface IRegister {
  name: string;
  email: string;
  password: string;
  role: 'ADMINISTRADOR' | 'USUARIO';
}
