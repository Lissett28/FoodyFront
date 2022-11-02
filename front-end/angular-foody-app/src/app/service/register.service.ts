import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterInstructions } from '../common/register-instructions';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  // base url
  private baseUrl ="http://localhost:8080/api/v1/registration"

  constructor(private httpClient:HttpClient) { }

  register(registerInstructions:RegisterInstructions){
    this.httpClient.post<RegisterInstructions>(this.baseUrl,registerInstructions).subscribe(
      (res) =>{
        if(res){
          console.log(res);
        }
      },
      (error) =>{
        console.log(error);
      }

    );
  }
}
