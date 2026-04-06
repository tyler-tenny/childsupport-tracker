import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { CaseListComponent } from './components/case-list/case-list.component';
import { CaseDetailComponent } from './components/case-detail/case-detail.component';
import { inject } from '@angular/core';
import { AuthService } from './services/auth.service';
import { Router } from '@angular/router';

const authGuard = () => {
  const auth = inject(AuthService);
  const router = inject(Router);
  if (auth.isLoggedIn()) return true;
  return router.parseUrl('/login');
};

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'cases', component: CaseListComponent, canActivate: [authGuard] },
  { path: 'cases/:id', component: CaseDetailComponent, canActivate: [authGuard] },
  { path: '', redirectTo: '/cases', pathMatch: 'full' }
];
