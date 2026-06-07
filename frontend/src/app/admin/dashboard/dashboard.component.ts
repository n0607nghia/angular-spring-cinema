import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  template: `
    <h1 class="text-white fw-bold mb-4">Dashboard</h1>
    <div class="row g-4">
      <div class="col-md-4">
        <div class="stat-card rounded-4 p-4">
          <p class="text-white-50 mb-1">Total Bookings</p>
          <h2 class="text-white fw-bold">—</h2>
        </div>
      </div>
      <div class="col-md-4">
        <div class="stat-card rounded-4 p-4">
          <p class="text-white-50 mb-1">Active Screenings</p>
          <h2 class="text-white fw-bold">—</h2>
        </div>
      </div>
      <div class="col-md-4">
        <div class="stat-card rounded-4 p-4">
          <p class="text-white-50 mb-1">Registered Users</p>
          <h2 class="text-white fw-bold">—</h2>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .stat-card {
      background: linear-gradient(135deg, rgba(0,0,0,0.85), rgba(0,0,0,0.6));
      border: 1px solid rgba(255,255,255,0.1);
    }
  `]
})
export class DashboardComponent {}
