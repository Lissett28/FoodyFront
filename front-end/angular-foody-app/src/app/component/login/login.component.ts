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


  constructor(private authService: AuthService, private http: HttpClient, private router: Router) {
  }

  login() {
    // use our authService to authenticate
    this.authService.authenticate(this.credentials,this.error,this.router);
    this.error = !this.authService.authenticated;
  }
  

}