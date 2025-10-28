import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

export interface LoginResponse {
  success: boolean;
  token?: string;
  role?: 'doctor' | 'patient';
  message?: string;
}

export interface User {
  username: string;
  password: string;
  role: 'doctor' | 'patient';
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly STORAGE_KEY = 'auth_token';
  private readonly ROLE_KEY = 'user_role';

  private readonly MOCK_USERS: User[] = [
    { username: 'doctor', password: 'doctor123', role: 'doctor' },
    { username: 'patient', password: 'patient123', role: 'patient' }
  ];

  constructor(private router: Router) {}

  login(username: string, password: string): LoginResponse {
    const user = this.MOCK_USERS.find(
      u => u.username === username && u.password === password
    );

    if (user) {
      const token = this.generateToken(username);
      localStorage.setItem(this.STORAGE_KEY, token);
      localStorage.setItem(this.ROLE_KEY, user.role);

      return {
        success: true,
        token,
        role: user.role
      };
    }

    return {
      success: false,
      message: 'Invalid username or password'
    };
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

  private generateToken(username: string): string {
    return btoa(`${username}:${Date.now()}`);
  }
}
