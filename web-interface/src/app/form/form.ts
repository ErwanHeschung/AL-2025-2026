import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { HealthDataService } from '../services/health-data';
import { FormService, FormData } from '../services/form.service';

@Component({
  selector: 'app-form',
  imports: [CommonModule, FormsModule, MatIconModule],
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
  isLoading: boolean = false;
  showSuccess: boolean = false;
  showError: boolean = false;
  errorMessage: string = '';

  constructor(
    private healthDataService: HealthDataService,
    private formService: FormService,
    private router: Router,
    private cdr: ChangeDetectorRef
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

    // Load existing form data for today if any
    this.loadExistingFormData();
  }

  loadExistingFormData(): void {
    this.isLoading = true;

    this.formService.getFormByDate(this.currentDate).subscribe({
      next: (formResponse) => {
        if (formResponse && formResponse.data) {
          try {
            const formData = JSON.parse(formResponse.data);

            // Set values with a slight delay to ensure DOM is ready
            setTimeout(() => {
              // Ensure values are numbers
              this.breathing = Number(formData.breathing);
              this.swelling = Number(formData.swelling);
              this.energy = Number(formData.energy);

              // Update descriptions
              if (this.breathing !== null && !isNaN(this.breathing)) {
                this.breathingDesc = this.healthDataService.getDescriptionForValue('breathing', this.breathing);
              }
              if (this.swelling !== null && !isNaN(this.swelling)) {
                this.swellingDesc = this.healthDataService.getDescriptionForValue('swelling', this.swelling);
              }
              if (this.energy !== null && !isNaN(this.energy)) {
                this.energyDesc = this.healthDataService.getDescriptionForValue('energy', this.energy);
              }

              this.isLoading = false;
              this.cdr.detectChanges();
            }, 100);
          } catch (error) {
            console.error('Error parsing form data:', error);
            this.isLoading = false;
          }
        } else {
          this.isLoading = false;
        }
      },
      error: (error) => {
        console.error('Error loading existing form:', error);
        this.isLoading = false;
      }
    });
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

    this.isLoading = true;

    const formData: FormData = {
      breathing: this.breathing!,
      swelling: this.swelling!,
      energy: this.energy!
    };

    this.formService.submitQuestionnaire(formData, this.currentDate).subscribe({
      next: (response) => {
        this.isLoading = false;
        this.showSuccess = true;
        setTimeout(() => {
          this.showSuccess = false;
        }, 2000);
      },
      error: (error) => {
        console.error('Error submitting form:', error);
        this.isLoading = false;
        this.showError = true;
        this.errorMessage = error.error?.message || 'Failed to submit questionnaire. Please try again.';
        setTimeout(() => {
          this.showError = false;
        }, 3000);
      }
    });
  }

}
