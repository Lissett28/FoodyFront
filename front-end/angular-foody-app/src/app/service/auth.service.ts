import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authenticated = false;
  sessionId: any = "";


  constructor(private http: HttpClient) {
  }

    authenticate(credentials,error,router){
        
        const usernamePassword = window.btoa(credentials.username + ':' + credentials.password);
        
        this.http.post<any>('http://localhost:8080/api/v1/login', usernamePassword).subscribe(
            (res) => {
            if(res) {
                this.sessionId = res.sessionId;

                sessionStorage.setItem(
                    'token',
                    this.sessionId
                );
                this.authenticated = true;
                
                router.navigateByUrl('/about');
                
            }
            },
            (error) =>{
                error = true;
                console.log('error');
                
            }

        );
    }
}



