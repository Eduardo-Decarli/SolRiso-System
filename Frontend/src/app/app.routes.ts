import { Routes } from '@angular/router';
import { Login } from './screen/login/login';
import { AdminDashboard } from './screen/admin-dashboard/admin-dashboard';

export const routes: Routes = [
  {
    path: 'login', component: Login
  },
  {
    path: 'inicio', component: AdminDashboard
  }

];
