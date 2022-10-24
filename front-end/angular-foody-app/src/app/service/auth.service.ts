import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authenticated:Subject<boolean> = new Subject<boolean>();
  sessionId: any = "";
  userDisplayName:Subject<string> = new Subject<string>();



  constructor(private http: HttpClient) {
  }

    authenticate(credentials,error,router){
        
        const usernamePassword = window.btoa(credentials.username + ':' + credentials.password);
        
        this.http.post<any>('http://localhost:8080/api/v1/login', usernamePassword).subscribe(
            (res) => {
            if(res) {
                this.sessionId = res.sessionId;
                this.userDisplayName.next(res.displayName);
                sessionStorage.setItem(
                    'token',
                    this.sessionId
                );
                this.authenticated.next(true);
                
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



