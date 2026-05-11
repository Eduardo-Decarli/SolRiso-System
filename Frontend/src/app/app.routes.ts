import { Routes } from '@angular/router';
import { Login } from './screen/login/login';
import { AdminDashboard } from './screen/admin-dashboard/admin-dashboard';
import { Register } from './screen/register/register';
import { NotFound } from './screen/not-found/not-found';
import { CreateReservation } from './screen/create-reservation/create-reservation';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'inicio',
    pathMatch: 'full',
  },
  {
    path: 'login',
    title: 'SolRiso - Login',
    component: Login,
  },
  {
    path: 'register',
    title: 'SolRiso - Register',
    component: Register,
  },
  {
    path: 'inicio',
    title: 'SolRiso - Inicio',
    component: AdminDashboard,
  },
  {
    path: 'cadastra-reserva',
    title: 'SolRiso - Cadastrar Reserva',
    component: CreateReservation,
  },
  {
    path: '**',
    title: 'Página não Encontrada',
    component: NotFound,
  },
];
