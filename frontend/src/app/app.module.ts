import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ComponentsComponent } from './core/components/components.component';
import { NavBarComponent } from './core/components/nav-bar/nav-bar.component';
import { MoviesComponent } from './core/components/movies/movies.component';

@NgModule({
  declarations: [
    AppComponent,
    ComponentsComponent,
    NavBarComponent,
    MoviesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
