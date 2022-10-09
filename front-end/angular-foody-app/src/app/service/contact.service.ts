import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Inquiry } from '../common/inquiry';
import { map, Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ContactService {

  // base url
  private baseUrl ="http://localhost:8080/api/"

  constructor(private httpClient:HttpClient) { }

  makeInquiry(inquiry:Inquiry):Observable<any>{
    const type = "contact/"
    const searchUrl = `${this.baseUrl}${type}make/`
    return this.httpClient.post<Inquiry>(this.baseUrl,inquiry);
  }
}
