import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {
  user = {
    username: '',
    password: ''
  };
  signinError: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  signin() {
    this.authService.signin(this.user).subscribe(
      (response: any) => {
        if (response.token && response.username && response.firstName && response.lastName && response.role) {
          const loggedInUser = {
            username: response.username,
            password: this.user.password,
            firstName: response.firstName,
            lastName: response.lastName,
            role: response.role,
            token: response.token
          };
          this.authService.setLoggedInUser(loggedInUser);

          // Redirect based on user role
          switch (loggedInUser.role) {
            case 'ADMIN':
              this.router.navigate(['/main']);
              break;
            case 'CAMPEUR':
              this.router.navigate(['/campeur-dashboard']);
              break;
            case 'RESPONSABLE':
              this.router.navigate(['/responsable-dashboard']);
              break;
            default:
              this.router.navigate(['/main']);
          }
        } else {
          this.signinError = response.message || 'Invalid username or password';
        }
      },
      (error: any) => {
        this.signinError = 'Invalid username or password';
      }
    );
  }
}
