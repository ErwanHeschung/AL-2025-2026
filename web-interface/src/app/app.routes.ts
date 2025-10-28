import { Routes } from '@angular/router';
import { LoginComponent } from './login/login';
import { FormComponent } from './form/form';
import { DashboardComponent } from './dashboard/dashboard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'form', component: FormComponent },
  { path: 'dashboard', component: DashboardComponent }
];
