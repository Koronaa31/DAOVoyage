import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public apiUrl: string;
  public users: Array<User> = [];

  constructor( private appConfig: AppConfigService, private http: HttpClient ) {
    this.apiUrl = `${this.appConfig.url}/users`
  }

  public subscribe(user) {
    this.http.post<User>(`${this.apiUrl}/subscribe`, user)
      .subscribe(user => this.users.push(user));
  }

  public connect(user) {
    this.http.post<User>(`${this.apiUrl}/login`, user)
      .subscribe(user => {
        this.users.push(user);
        location.replace(`lobby`);
        },
        error => alert('Identifiants incorrects'));
  }
}
