import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './public/home/home.component';
import { MoviesComponent } from './public/movies/movies.component';
import { PublicLayoutComponent } from './public/public-layout/public-layout.component';
import { AdminLayoutComponent } from './admin/admin-layout/admin-layout.component';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { RoleGuard } from './core/auth/role.guard';

const routes: Routes = [
  {
    path: '',
    component: PublicLayoutComponent,
    children: [
      { path: '', component: HomeComponent },
      { path: 'movies', component: MoviesComponent }
    ]
  },
  {
    path: 'admin',
    component: AdminLayoutComponent,
    canActivate: [RoleGuard],
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
