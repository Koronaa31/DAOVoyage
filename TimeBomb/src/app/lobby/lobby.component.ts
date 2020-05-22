import { Component, OnInit } from '@angular/core';
import { MatchService } from '../match.service';
import { UserService } from '../user.service';
import { Match } from '../match';
import { SseService } from '../sse.service';
import { AppConfigService } from '../app-config.service';

@Component({
  selector: 'app-lobby',
  templateUrl: './lobby.component.html',
  styleUrls: ['./lobby.component.css']
})
export class LobbyComponent implements OnInit {

  public match = new Match();

  constructor(public srvMatch: MatchService,
              public srvUser: UserService,
              private sseService: SseService,
              private appConfig: AppConfigService) {

  }

  ngOnInit(): void {
    alert('init');
    fetch("http://176.143.99.66:8080/api/matches/sse-stream",
          this.appConfig.httpOptionsSse)
      .then(resp => {
        try{
          alert('sse');
        this.sseService.
          getServerSentEvent("http://176.143.99.66:8080/api/matches/sse-stream")
            .subscribe(data => console.log(data));
          } catch {
            alert('oups');
            this.ngOnInit();}
      });

    this.srvMatch.reload();
    this.srvMatch.mine();
  }

  public ajouterMatch(){
    this.srvMatch.add(this.match);
    this.match = new Match();
  }

  public rejoindreMatch(match: Match){
    this.srvMatch.update(match);
  }

  public supprimerMatch(match: Match){
    this.srvMatch.delete(match);
  }

}
