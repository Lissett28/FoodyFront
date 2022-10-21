import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {
  constructor(private router: Router){}
  
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      // we are allowed to go to login
      if(state.url == "/login"){
        return true;
      }

      // if you try to go different page 
      // then we have to check if token is there
      let token = sessionStorage.getItem('token');

      // if token is not there that means you are not logged in 
      if(!token){
        return this.router.parseUrl('/login');
      }

      return true;
  }
  
}
