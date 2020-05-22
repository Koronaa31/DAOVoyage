import { Injectable, Injector } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { UserService } from './user.service';
import { AppModule } from './app.module';


@Injectable({
  providedIn: 'root'
})
export class AppConfigService {

  public url: string = AppModule.url;
  public httpOptions: Object = null;
  public httpOptionsSse = null;

  constructor(private injector: Injector, private srvUser: UserService) {
    this.httpOptionsSse = { headers: {
      Authorization: 'Basic ' + btoa(
        `${ srvUser.user.username }:${ srvUser.user.password }`)
    }};

  }

  public options() {
    const srvUser = this.injector.get(UserService);
    let myHeaders: HttpHeaders = new HttpHeaders();
    myHeaders = myHeaders.append('Authorization', 'Basic ' + btoa(
      `${ srvUser.user.username }:${ srvUser.user.password }`
    ));
    /*myHeaders = myHeaders.append('Authorization', 'Basic ' + btoa(
      `Koronaa:sopra`
    ));*/

    this.httpOptions = { headers: myHeaders };
    return this.httpOptions;
  }

}
