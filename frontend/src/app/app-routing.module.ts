import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ComponentsComponent } from './core/components/components.component';
import { MoviesComponent } from './core/components/movies/movies.component';

const routes: Routes = [
  { path: '', component: ComponentsComponent },
  { path: 'movies', component: MoviesComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
