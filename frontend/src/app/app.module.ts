import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './public/home/home.component';
import { MoviesComponent } from './public/movies/movies.component';
import { NavBarComponent } from './shared/nav-bar/nav-bar.component';
import { PublicLayoutComponent } from './public/public-layout/public-layout.component';
import { AdminLayoutComponent } from './admin/admin-layout/admin-layout.component';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { JwtInterceptor } from './core/interceptors/jwt.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MoviesComponent,
    NavBarComponent,
    PublicLayoutComponent,
    AdminLayoutComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
