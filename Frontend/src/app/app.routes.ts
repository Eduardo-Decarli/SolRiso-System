import { Routes } from '@angular/router';
import { Login } from './screen/login/login';
import { AdminDashboard } from './screen/admin-dashboard/admin-dashboard';
import { Register } from './screen/register/register';

export const routes: Routes = [
  {
    path: 'login',
    component: Login,
  },
  {
    path: 'register',
    component: Register,
  },
  {
    path: 'inicio',
    component: AdminDashboard,
  },
];
