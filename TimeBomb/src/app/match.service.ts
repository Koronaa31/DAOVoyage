import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { UserService } from './user.service';
import { Match } from './match';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private apiUrl: string = "";
  public matches: Array<Match> = null;
  public httpOptions: Object = null;

  constructor(private appConfig: AppConfigService,
              private http: HttpClient,
              private srvUser: UserService) {

    this.apiUrl = `${ this.appConfig.url }/matches`;
    let myHeaders: HttpHeaders = new HttpHeaders();
        myHeaders = myHeaders
          .append('Authorization', 'Basic ' + btoa(this.srvUser.user.username +":"+ this.srvUser.user.password));
        this.httpOptions = { headers: myHeaders };
  }

  public reload() {
    this.http.get<Array<Match>>(this.apiUrl, this.httpOptions)
      .subscribe(respMatches => this.matches = respMatches);
  }

  public add(match: Match) {
    this.http.post<Match>(this.apiUrl, match, this.httpOptions)
      .subscribe(respMatch => this.matches.push(respMatch));
  }

  public delete(match: Match) {
    this.http.delete<Boolean>(`${ this.apiUrl }/${ match.id }`, this.httpOptions)
      .subscribe(resp => {
        if (resp) {
          let index = this.matches.indexOf(match);
          this.matches.splice(index, 1);
        }
      });
  }
}
