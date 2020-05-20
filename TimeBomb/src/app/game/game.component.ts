import { Component, OnInit } from '@angular/core';
import { CardService } from '../card.service';
import { MatchService } from '../match.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  constructor(private srvCard: CardService, private srvUser: UserService, private srvMatch?: MatchService) { }

  ngOnInit() {
    this.srvMatch.reload();
  }

}