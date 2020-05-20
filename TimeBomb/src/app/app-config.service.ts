import { Injectable } from '@angular/core';



@Injectable({
  providedIn: 'root'
})
export class AppConfigService {

  public url: string = "http://176.143.99.66:8080/api";

  constructor() { }

}
