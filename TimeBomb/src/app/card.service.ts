import { Injectable } from '@angular/core';
import { Card } from './card';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CardService {
  private apiUrl: string = "";
  public cards: Array<Card> = null;

  constructor(public appConfig: AppConfigService, private http: HttpClient) {
    this.apiUrl = `${ this.appConfig.url }/matches/1`;
  }

  public reload() {
  /*  this.http.get<Array<Card>>(this.apiUrl)
      .subscribe( resp => this.cards = resp.match.deck );*/
  }
}
