import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
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

    return this.http.post<FormResponse>(this.API_URL, request);
  }

  getPatientForms(limit: number = 10): Observable<FormResponse[]> {
    const token = this.authService.getToken();
    if (!token) {
      throw new Error('No authentication token found');
    }

    const payload = this.decodeToken(token);
    const userId = payload.id;

    return this.http.get<FormResponse[]>(
      `${this.API_URL}/patient/${userId}?limit=${limit}`
    );
  }

  getFormByDate(date: string): Observable<FormResponse | null> {
    const token = this.authService.getToken();
    if (!token) {
      throw new Error('No authentication token found');
    }

    const payload = this.decodeToken(token);
    const userId = payload.id;

    return this.http.get<FormResponse>(
      `${this.API_URL}/patient-date/${userId}/${date}`
    );
  }

  getPatientFormByDate(patientId: string, date: string): Observable<FormResponse | null> {
    return this.http.get<FormResponse>(
      `${this.API_URL}/patient-date/${patientId}/${date}`
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
