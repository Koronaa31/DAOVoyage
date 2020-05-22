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

  constructor(private injector: Injector) {}
    
  public options() {
    const srvUser = this.injector.get(UserService);
    let myHeaders: HttpHeaders = new HttpHeaders();
    myHeaders = myHeaders.append('Authorization', 'Basic ' + btoa(
      `${ srvUser.user.username }:${ srvUser.user.password }`
      ));

    this.httpOptions = { headers: myHeaders };
    return this.httpOptions;
  }
}
