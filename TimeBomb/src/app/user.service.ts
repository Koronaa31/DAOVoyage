import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from './user';
import { AppModule } from './app.module';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public url: string = AppModule.url + "/users";
  public user: User;
  public token;
  public newUser: User;

  constructor( private http: HttpClient, private router: Router ) {
  }

  public subscribe(newUser) {
    this.http.post<User>(`${this.url}/subscribe`, newUser)
      .subscribe(respUser => {
        this.newUser = respUser;
        alert('Inscription validée... Go connexion')
      });
  }

  public connect(user) {
    this.http.post<User>(`${this.url}/login`, user)
      .subscribe(respUser => {
        this.user = respUser;
        this.user.username = user.username;
        this.user.password = user.password;
        this.router.navigate(['/lobby']);
        },
        error => alert('Identifiants incorrects'));
  }
}
