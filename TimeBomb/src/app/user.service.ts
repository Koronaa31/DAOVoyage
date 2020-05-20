import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public apiUrl: string;
  public user: User;
  public newUser: User;

  constructor( private appConfig: AppConfigService, private http: HttpClient ) {
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
        location.replace(`lobby`);
        },
        error => alert('Identifiants incorrects'));
  }
}
