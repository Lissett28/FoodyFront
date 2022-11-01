import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  error = false;
  credentials = {username: '', password: ''};


  ngOnInit(): void {
    this.error = false;
  }

  constructor(private authService: AuthService, private http: HttpClient, private router: Router) {
  }

  login() {
    // use our authService to authenticate
    this.authService.authenticate(this.credentials,this.error,this.router);
    this.authService.authenticated.subscribe(res => this.error = !res);
  }
  

}