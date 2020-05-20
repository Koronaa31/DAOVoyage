import { Component, OnInit } from '@angular/core';
import { MatchService } from '../match.service';
import { UserService } from '../user.service';
import { Match } from '../match';

@Component({
  selector: 'app-lobby',
  templateUrl: './lobby.component.html',
  styleUrls: ['./lobby.component.css']
})
export class LobbyComponent implements OnInit {

  public match = new Match();

  constructor(public srvMatch: MatchService, public srvUser: UserService) {

  }

  ngOnInit(): void {
    this.srvMatch.reload();
  }

  public ajouterMatch(){
    this.srvMatch.add(this.match);
    this.match = new Match();
  }

  public supprimerMatch(match: Match){
    this.srvMatch.delete(match);
  }

}
