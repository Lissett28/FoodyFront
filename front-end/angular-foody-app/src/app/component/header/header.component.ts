import { Component, OnInit } from '@angular/core';
import { Userprofile } from 'src/app/common/userprofile';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  signin:boolean;
  displayName:String;
  constructor(private authService:AuthService) { }

  ngOnInit(): void {
    let user = JSON.parse(sessionStorage.getItem("userDetails")) as Userprofile;
    if(!user){
      this.signin = false;
    }else{
      this.signin = true;
    }
    this.displayName = user.displayName;
  }

}
