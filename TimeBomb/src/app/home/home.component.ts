import { Component, OnInit } from '@angular/core';
import { AppConfigService } from '../app-config.service';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public user: User = new User();
  public newUser: User = new User;

  constructor( private srvUser: UserService ) {  
  }
    
  ngOnInit(): void {
  }

  public addUser() {
    this.srvUser.subscribe(this.newUser);
    this.newUser = new User();
  }

  public connexion() {
    this.srvUser.connect(this.user);
    this.user = new User();
  }
}
