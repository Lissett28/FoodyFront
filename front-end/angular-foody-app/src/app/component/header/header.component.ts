import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { SearchServiceService } from 'src/app/service/search-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  signin:boolean;
  displayName:string;
  constructor(private authService:AuthService) { }

  ngOnInit(): void {
    this.authService.authenticated.subscribe(res => this.signin = res);
    this.authService.userDisplayName.subscribe(res => this.displayName = res);
  }

}
