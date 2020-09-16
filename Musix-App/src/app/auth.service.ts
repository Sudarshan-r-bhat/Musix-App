import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subscriber } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // loginUrl: string = "http://localhost:8080/login";
  // injecting the http client
  constructor(private _http: HttpClient, private _router: Router) { }

  // Authentication has to happen at the server-end. 
  // but for now, we authenticate here, only for default admin user.
  loginUser(user) {
    if(user.email == 'admin' && user.password == 'admin')
      return {token: "secret token"};
    // return this._http.post<any>(this.loginUrl, user);
    
  }
  registerUser(user) {
    // return this._http.post<any>(this.registerUrl, user);
    // return {token: "secret token"};
    return true;
  }

  logoutUser() {
    localStorage.removeItem('token');
    this._router.navigate(['/dashboard']);
  }

  private getToken(): boolean {
    // returns true if the token is present.
    return !!localStorage.getItem('token');
  }
  loggedIn() {
    return this.getToken();
  }


}
