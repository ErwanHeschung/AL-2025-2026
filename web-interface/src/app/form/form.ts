import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { HealthDataService, QuestionnaireResponse } from '../services/health-data';

@Component({
  selector: 'app-form',
  imports: [CommonModule, MatIconModule],
  templateUrl: './form.html',
  styleUrl: './form.css'
})
export class FormComponent implements OnInit {
  currentDate: string = '';
  displayDate: string = '';
  breathing: number | null = null;
  swelling: number | null = null;
  energy: number | null = null;
  breathingDesc: string = '';
  swellingDesc: string = '';
  energyDesc: string = '';
  isLoading: boolean = true;
  showSuccess: boolean = false;
  showError: boolean = false;
  isEditMode: boolean = false;

  constructor(
    private healthDataService: HealthDataService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const today = new Date();
    this.currentDate = today.toISOString().split('T')[0];
    this.displayDate = today.toLocaleDateString('en-US', {
      weekday: 'long',
      year: 'numeric',
      month: 'long',
      day: 'numeric'
    });

    const existingResponse = this.healthDataService.getQuestionnaireResponse(this.currentDate);
    if (existingResponse) {
      this.breathing = existingResponse.breathing;
      this.swelling = existingResponse.swelling;
      this.energy = existingResponse.energy;
      this.isEditMode = true;
      this.updateDescriptions();
    }

    setTimeout(() => {
      this.isLoading = false;
    }, 500);
  }

  selectValue(question: string, value: number): void {
    if (question === 'breathing') {
      this.breathing = value;
      this.breathingDesc = this.healthDataService.getDescriptionForValue('breathing', value);
    } else if (question === 'swelling') {
      this.swelling = value;
      this.swellingDesc = this.healthDataService.getDescriptionForValue('swelling', value);
    } else if (question === 'energy') {
      this.energy = value;
      this.energyDesc = this.healthDataService.getDescriptionForValue('energy', value);
    }
  }

  isSelected(question: string, value: number): boolean {
    if (question === 'breathing') return this.breathing === value;
    if (question === 'swelling') return this.swelling === value;
    if (question === 'energy') return this.energy === value;
    return false;
  }

  isFormValid(): boolean {
    return this.breathing !== null && this.swelling !== null && this.energy !== null;
  }

  onSubmit(): void {
    if (!this.isFormValid()) return;

    const response: QuestionnaireResponse = {
      date: this.currentDate,
      breathing: this.breathing!,
      swelling: this.swelling!,
      energy: this.energy!
    };

    try {
      this.healthDataService.saveQuestionnaireResponse(response);
      this.showSuccess = true;
      setTimeout(() => {
        this.showSuccess = false;
        this.router.navigate(['/dashboard']);
      }, 1500);
    } catch (error) {
      this.showError = true;
      setTimeout(() => {
        this.showError = false;
      }, 3000);
    }
  }

  private updateDescriptions(): void {
    if (this.breathing) {
      this.breathingDesc = this.healthDataService.getDescriptionForValue('breathing', this.breathing);
    }
    if (this.swelling) {
      this.swellingDesc = this.healthDataService.getDescriptionForValue('swelling', this.swelling);
    }
    if (this.energy) {
      this.energyDesc = this.healthDataService.getDescriptionForValue('energy', this.energy);
    }
  }
}
