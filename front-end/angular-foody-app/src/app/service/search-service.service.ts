import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Business } from '../common/business';

@Injectable({
  providedIn: 'root'
})
export class SearchServiceService {
  // base url
  private baseUrl ="http://localhost:8080/api/v1/"
  // type of api call 
  

  constructor(private httpClient:HttpClient) { }

  searchBusinesses(address:string, radius: string) :Observable<Business[]>{
    const type = "locate/"
    const searchUrl = `${this.baseUrl}${type}find/${address}&${radius}`
    return this.httpClient.get<GetResponseBusinesses>(searchUrl).pipe(
      map(Response => Response.businesses)
    );
  }
}
interface GetResponseBusinesses{
  businesses:Business[];
}
