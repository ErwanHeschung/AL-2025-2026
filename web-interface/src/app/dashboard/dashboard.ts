import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { HealthDataService, QuestionnaireResponse, DailyMedicalData, HourlyData } from '../services/health-data';
import { PatientService, Patient } from '../services/patient.service';

interface MetricStats {
  min: number;
  avg: number;
  max: number;
}

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, FormsModule, MatIconModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class DashboardComponent implements OnInit {
  patients: Patient[] = [];
  selectedPatientId: string = '';
  selectedDate: string = '';
  questionnaireData: QuestionnaireResponse | null = null;
  medicalData: DailyMedicalData | null = null;
  heartRateStats: MetricStats = { min: 0, avg: 0, max: 0 };
  spo2Stats: MetricStats = { min: 0, avg: 0, max: 0 };
  activityStats: MetricStats = { min: 0, avg: 0, max: 0 };

  constructor(
    private healthDataService: HealthDataService,
    private patientService: PatientService
  ) {}

  ngOnInit(): void {
    const today = new Date();
    this.selectedDate = today.toISOString().split('T')[0];
    this.loadPatients();
  }

  loadPatients(): void {
    this.patientService.getPatients().subscribe({
      next: (patients) => {
        this.patients = patients;
      },
      error: (error) => {
        console.error('Error loading patients:', error);
      }
    });
  }

  onPatientChange(): void {
    if (this.selectedPatientId) {
      this.loadData();
    } else {
      this.clearData();
    }
  }

  loadData(): void {
    if (!this.selectedPatientId) {
      this.clearData();
      return;
    }

    // TODO: Load data for the selected patient
    this.questionnaireData = this.healthDataService.getQuestionnaireResponse(this.selectedDate);
    this.medicalData = this.healthDataService.getMedicalData(this.selectedDate);

    if (this.medicalData) {
      this.calculateStats();
    }
  }

  clearData(): void {
    this.questionnaireData = null;
    this.medicalData = null;
    this.heartRateStats = { min: 0, avg: 0, max: 0 };
    this.spo2Stats = { min: 0, avg: 0, max: 0 };
    this.activityStats = { min: 0, avg: 0, max: 0 };
  }

  onDateChange(): void {
    this.loadData();
  }

  previousDay(): void {
    const date = new Date(this.selectedDate);
    date.setDate(date.getDate() - 1);
    this.selectedDate = date.toISOString().split('T')[0];
    this.loadData();
  }

  nextDay(): void {
    const date = new Date(this.selectedDate);
    date.setDate(date.getDate() + 1);
    this.selectedDate = date.toISOString().split('T')[0];
    this.loadData();
  }

  getDescriptionForValue(question: string, value: number): string {
    return this.healthDataService.getDescriptionForValue(question, value);
  }

  getProgressWidth(value: number): string {
    return `${(value / 9) * 100}%`;
  }

  getProgressColor(question: string, value: number): string {
    if (question === 'breathing') {
      return value <= 3 ? '#4CAF50' : value <= 6 ? '#FF9800' : '#F44336';
    } else if (question === 'swelling') {
      return value <= 3 ? '#4CAF50' : value <= 6 ? '#FF9800' : '#F44336';
    } else if (question === 'energy') {
      return value <= 3 ? '#F44336' : value <= 6 ? '#FF9800' : '#4CAF50';
    }
    return '#4CAF50';
  }

  private calculateStats(): void {
    if (!this.medicalData) return;

    const heartRates = this.medicalData.hourlyData.map(d => d.heartRate);
    const spo2Values = this.medicalData.hourlyData.map(d => d.spo2);
    const activityValues = this.medicalData.hourlyData.map(d => d.activity);

    this.heartRateStats = this.getStats(heartRates);
    this.spo2Stats = this.getStats(spo2Values);
    this.activityStats = this.getStats(activityValues);
  }

  private getStats(values: number[]): MetricStats {
    if (values.length === 0) return { min: 0, avg: 0, max: 0 };

    const min = Math.min(...values);
    const max = Math.max(...values);
    const avg = Math.round(values.reduce((a, b) => a + b, 0) / values.length);

    return { min, avg, max };
  }

  getChartBars(metric: 'heartRate' | 'spo2' | 'activity'): { height: string; value: number }[] {
    if (!this.medicalData) return [];

    const values = this.medicalData.hourlyData.map(d => d[metric]);
    const max = Math.max(...values);

    return values.map(value => ({
      height: `${(value / max) * 100}%`,
      value
    }));
  }

  getHeartRateStatus(hr: number): { color: string; label: string } {
    if (hr < 60) return { color: 'orange', label: 'Low' };
    if (hr <= 100) return { color: 'green', label: 'Normal' };
    return { color: 'red', label: 'High' };
  }

  getSpo2Status(spo2: number): { color: string; label: string } {
    if (spo2 < 95) return { color: 'orange', label: 'Low' };
    return { color: 'green', label: 'Normal' };
  }

  getActivityLevel(activity: number): { color: string; label: string; badge: string } {
    if (activity < 30) return { color: 'blue', label: 'Low', badge: 'low' };
    if (activity < 60) return { color: 'orange', label: 'Moderate', badge: 'moderate' };
    return { color: 'red', label: 'High', badge: 'high' };
  }
}
