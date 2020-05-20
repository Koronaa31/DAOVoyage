import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public apiUrl: string;
  public user: User;
  public token;
  public newUser: User;

  constructor( private appConfig: AppConfigService, private http: HttpClient, private router: Router ) {
    this.apiUrl = `${this.appConfig.url}/users`
  }

  public subscribe(newUser) {
    this.http.post<User>(`${this.apiUrl}/subscribe`, newUser)
      .subscribe(respUser => this.newUser = respUser);
  }

  public connect(user) {
    this.http.post<User>(`${this.apiUrl}/login`, user)
      .subscribe(respUser => {
        this.user = respUser;
        this.user.username = user.username;
        this.user.password = user.password;
        this.router.navigate(['/lobby']);
        },
        error => alert('Identifiants incorrects'));
  }
}
