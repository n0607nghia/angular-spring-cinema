import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private currentUserSubject = new BehaviorSubject<any>(null);
  currentUser$ = this.currentUserSubject.asObservable();

  login(email: string, password: string): void {
    // TODO: call POST /auth/login, store JWT
  }

  logout(): void {
    localStorage.removeItem('token');
    this.currentUserSubject.next(null);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  getRole(): string | null {
    const token = this.getToken();
    if (!token) return null;
    // TODO: decode JWT and return role
    return null;
  }
}
