import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { FalseLiteral } from 'typescript';

@Component({
  templateUrl: './login.component.html'
})
export class LoginComponent {
  error = false;

  credentials = {username: '', password: ''};

  constructor(private app: AuthService, private http: HttpClient, private router: Router) {
  }

  login() {
    this.app.authenticate(this.credentials, () => {
        this.router.navigateByUrl('/login');
    });
    return false;
  }

}