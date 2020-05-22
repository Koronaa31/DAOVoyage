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
  public myMatch: Match = null;
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

  public reloadMatch(match: Match) {
    this.http.get<Array<Match>>(`${ this.apiUrl }/${ match.id }`, this.appConfig.options())
      .subscribe(resp => { return resp });
  }

  public mine() {
    this.http.get<Match>(`${ this.apiUrl }/mine`, this.appConfig.options())
      .subscribe(resp => {
        this.myMatch = resp;
      });
  }

  public add(match: Match) {
    this.http.post<Match>(this.apiUrl, match, this.appConfig.options())
      .subscribe(respMatch => this.matches.push(respMatch));
  }

  public update(match: Match) {
    this.http.put<Match>(`${ this.apiUrl }/${ match.id }`, null, this.appConfig.options())
      .subscribe(resp => {
        this.myMatch = resp;
        this.reload();
      });
  }

  public delete(match: Match) {
    this.http.delete<Boolean>(`${ this.apiUrl }/${ match.id }`, this.appConfig.options())
      .subscribe(resp => this.matches.splice(this.matches.indexOf(match), 1));
  }

  public sse(){
    this.http.get(`${ this.apiUrl }/sse-stream`)
    .subscribe();
  }
}
