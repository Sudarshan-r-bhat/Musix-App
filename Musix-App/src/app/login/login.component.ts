import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { Injector } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  // todo: property binding on ths object
  private user: Object = {
    email: 'admin',
    password: 'admin'
  }
  // todo: 2 way binding on this variable.
  private displayError: string = '';

  constructor(private _injector: Injector, private _router: Router) { }

  ngOnInit(): void {
  }

  loginUser() {
    const authService = this._injector.get(AuthService);
    authService.loginUser(this.user);
    localStorage.setItem('token', 'secretkey');
    this._router.navigate(['/user']);

    /*this._authService
      .loginUser(this.user)
      .subscribe(res => {
        localStorage.setItem('token', res.token);
        this._router.navigate(['/user']);
      }, err => {
        this.displayError = err.message();
      });*/  
  }

}
