import { Component, OnInit } from '@angular/core';
import { Userprofile } from 'src/app/common/userprofile';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  user:Userprofile = new Userprofile();

  constructor(private auth:AuthService) { }

  ngOnInit(): void {
    this.auth.userprofile.subscribe(
      res => {this.user = res,
      console.log(this.user)}
      );
  }

}
