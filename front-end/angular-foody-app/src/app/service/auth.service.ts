import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authenticated = false;

  constructor(private http: HttpClient) {
  }

  authenticate(credentials, callback) {
        console.log(credentials? window.btoa(credentials.username + ':' + credentials.password) : "nothing there");
        const headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + window.btoa(credentials.username + ':' + credentials.password)
        } : {});

        this.http.get('http://localhost:8080/user', {headers: headers}).subscribe(response => {
            if (response['name']) {
                this.authenticated = true;
            } else {
                this.authenticated = false;
            }
            return callback && callback();
        });

    }
}


