import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth';

export interface Patient {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private readonly API_URL = 'http://localhost:8084/patients';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  getPatients(): Observable<Patient[]> {
    const token = this.authService.getToken();
    if (!token) {
      throw new Error('No authentication token found');
    }

    // Decode JWT to get user ID
    const payload = this.decodeToken(token);
    const userId = payload.id;

    const headers = new HttpHeaders({
      'X-User-Id': userId
    });

    return this.http.get<Patient[]>(this.API_URL, { headers });
  }

  private decodeToken(token: string): any {
    const parts = token.split('.');
    if (parts.length !== 3) {
      throw new Error('Invalid JWT token');
    }
    const payload = parts[1];
    const decoded = atob(payload);
    return JSON.parse(decoded);
  }
}
