import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  error = false;
  credentials = {username: '', password: ''};
  
  constructor() { }

  ngOnInit(): void {
  }

  signup(){

  }
}
