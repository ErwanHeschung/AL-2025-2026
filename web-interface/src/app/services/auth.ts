import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

export interface LoginResponse {
  success: boolean;
  token?: string;
  role?: 'doctor' | 'patient';
  message?: string;
}

interface JwtPayload {
  sub: string;
  id: string;
  role: string;
  iat: number;
  exp: number;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly STORAGE_KEY = 'auth_token';
  private readonly ROLE_KEY = 'user_role';
  private readonly API_URL = 'http://localhost:8084/auth';

  constructor(
    private router: Router,
    private http: HttpClient
  ) {}

  login(email: string, password: string): Observable<LoginResponse> {
    return this.http.post(
      `${this.API_URL}/login`,
      { email, password },
      { responseType: 'text' }
    ).pipe(
      map((token: string) => {
        // Decode JWT to get role
        const payload = this.decodeToken(token);
        const role = this.mapRole(payload.role);

        // Store token and role
        localStorage.setItem(this.STORAGE_KEY, token);
        localStorage.setItem(this.ROLE_KEY, role);

        return {
          success: true,
          token,
          role
        };
      }),
      catchError((error: HttpErrorResponse) => {
        return throwError(() => ({
          success: false,
          message: error.error || 'Invalid email or password'
        }));
      })
    );
  }

  logout(): void {
    localStorage.removeItem(this.STORAGE_KEY);
    localStorage.removeItem(this.ROLE_KEY);
    this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem(this.STORAGE_KEY);
  }

  getUserRole(): 'doctor' | 'patient' | null {
    const role = localStorage.getItem(this.ROLE_KEY);
    return role as 'doctor' | 'patient' | null;
  }

  getToken(): string | null {
    return localStorage.getItem(this.STORAGE_KEY);
  }

  private decodeToken(token: string): JwtPayload {
    const parts = token.split('.');
    if (parts.length !== 3) {
      throw new Error('Invalid JWT token');
    }
    const payload = parts[1];
    const decoded = atob(payload);
    return JSON.parse(decoded);
  }

  private mapRole(backendRole: string): 'doctor' | 'patient' {
    // Map backend roles (DOCTOR, NURSE, etc.) to frontend roles
    if (backendRole === 'DOCTOR' || backendRole === 'NURSE') {
      return 'doctor';
    }
    return 'patient';
  }
}
