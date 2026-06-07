import { Component } from '@angular/core';

@Component({
  selector: 'app-public-layout',
  template: `
    <app-nav-bar></app-nav-bar>
    <main class="container py-4 py-md-5">
      <router-outlet></router-outlet>
    </main>
  `
})
export class PublicLayoutComponent {}
