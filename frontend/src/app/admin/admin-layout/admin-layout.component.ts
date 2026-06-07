import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-layout',
  template: `
    <div class="d-flex">
      <nav class="admin-sidebar p-3">
        <h5 class="text-white fw-bold mb-4">⚙ Admin</h5>
        <ul class="nav flex-column gap-2">
          <li><a class="nav-link text-white-50" routerLink="/admin/dashboard" routerLinkActive="active">Dashboard</a></li>
          <li><a class="nav-link text-white-50" routerLink="/admin/screenings" routerLinkActive="active">Screenings</a></li>
          <li><a class="nav-link text-white-50" routerLink="/admin/users" routerLinkActive="active">Users</a></li>
          <li><a class="nav-link text-white-50" routerLink="/admin/bookings" routerLinkActive="active">Bookings</a></li>
        </ul>
      </nav>
      <main class="flex-grow-1 p-4">
        <router-outlet></router-outlet>
      </main>
    </div>
  `,
  styles: [`
    .admin-sidebar {
      min-height: 100vh;
      width: 220px;
      background: linear-gradient(180deg, #111 0%, #1a1a1a 100%);
      border-right: 1px solid rgba(255,255,255,0.08);
    }
    .nav-link.active { color: #ff8a00 !important; }
  `]
})
export class AdminLayoutComponent {}
