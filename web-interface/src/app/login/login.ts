import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../services/auth';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule, MatIconModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';
  showPassword: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit(): void {
    if (!this.username || !this.password) {
      this.errorMessage = 'Please enter email and password';
      return;
    }

    this.authService.login(this.username, this.password).subscribe({
      next: (result) => {
        if (result.success && result.role) {
          if (result.role === 'doctor') {
            this.router.navigate(['/dashboard']);
          } else {
            this.router.navigate(['/form']);
          }
        }
      },
      error: (error) => {
        this.errorMessage = error.message || 'Login failed';
      }
    });
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }
}
