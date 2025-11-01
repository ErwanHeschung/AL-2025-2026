import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from './auth';

export interface FormData {
  breathing: number;
  swelling: number;
  energy: number;
}

export interface FormRequest {
  patientId: string;
  issuerId: string;
  date: string;
  data: string;
  type: string;
}

export interface FormResponse {
  id: string;
  patientId: string;
  issuerId: string;
  date: string;
  data: string;
  type: string;
}

@Injectable({
  providedIn: 'root'
})
export class FormService {
  private readonly API_URL = 'http://localhost:8084/forms';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  submitQuestionnaire(formData: FormData, date: string): Observable<FormResponse> {
    const token = this.authService.getToken();
    if (!token) {
      throw new Error('No authentication token found');
    }

    // Decode JWT to get user ID
    const payload = this.decodeToken(token);
    const userId = payload.id;

    // Convert form data to JSON string (without the date)
    const dataString = JSON.stringify(formData);

    const request: FormRequest = {
      patientId: userId,
      issuerId: userId,
      date: date,
      data: dataString,
      type: 'daily_questionnaire'
    };

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post<FormResponse>(this.API_URL, request, { headers });
  }

  getPatientForms(limit: number = 10): Observable<FormResponse[]> {
    const token = this.authService.getToken();
    if (!token) {
      throw new Error('No authentication token found');
    }

    const payload = this.decodeToken(token);
    const userId = payload.id;

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<FormResponse[]>(
      `${this.API_URL}/patient/${userId}?limit=${limit}`,
      { headers }
    );
  }

  getFormByDate(date: string): Observable<FormResponse | null> {
    const token = this.authService.getToken();
    if (!token) {
      throw new Error('No authentication token found');
    }

    const payload = this.decodeToken(token);
    const userId = payload.id;

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<FormResponse>(
      `${this.API_URL}/patient/${userId}?date=${date}`,
      { headers }
    ).pipe(
      catchError(error => {
        if (error.status === 404) {
          return of(null);
        }
        throw error;
      })
    );
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
