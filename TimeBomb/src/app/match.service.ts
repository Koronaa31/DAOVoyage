import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { Match } from './match';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private apiUrl: string = "";
  public matches: Array<Match> = null;

  constructor(private appConfig: AppConfigService,
              private http: HttpClient) {

    this.apiUrl = `${ this.appConfig.url }/matches`;
  }

  public reload() {
    this.http.get<Array<Match>>(this.apiUrl)
      .subscribe(respMatches => this.matches = respMatches);
  }

  public add(match: Match) {
    this.http.post<Match>(this.apiUrl, match)
      .subscribe(respMatch => this.matches.push(respMatch));
  }

  public delete(match: Match) {
    this.http.delete<Boolean>(`${ this.apiUrl }/${ match.id }`)
      .subscribe(resp => {
        if (resp) {
          let index = this.matches.indexOf(match);
          this.matches.splice(index, 1);
        }
      });
  }
}
