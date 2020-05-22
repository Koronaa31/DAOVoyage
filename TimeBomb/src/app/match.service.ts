import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { UserService } from './user.service';
import { Match } from './match';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Card } from './card';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private apiUrl: string = "";
  public matches: Array<Match> = null;
  public httpOptions: Object = null;
  public cards: Array<Card> = null;

  constructor(private appConfig: AppConfigService,
              private http: HttpClient) {

    this.apiUrl = `${ this.appConfig.url }/matches`;
  }

  public reload() {
    this.http.get<Array<Match>>(this.apiUrl, this.appConfig.options())
      .subscribe(respMatches => this.matches = respMatches);
  }

  public add(match: Match) {
    this.http.post<Match>(this.apiUrl, match, this.appConfig.options())
      .subscribe(respMatch => this.matches.push(respMatch));
  }

  public delete(match: Match) {
    this.http.delete<Boolean>(`${ this.apiUrl }/${ match.id }`, this.appConfig.options())
      .subscribe(resp => this.matches.splice(this.matches.indexOf(match), 1));
  }
}
