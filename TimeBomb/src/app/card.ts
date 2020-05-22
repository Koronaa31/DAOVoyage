import { Match } from './match';
import { User } from './user';
import { UserService } from './user.service';

export class Card {
    public image?: string;

    constructor(public id?: number,
                public revealed?: boolean,
                public type?: string,
                public matchId?: Match,
                public ownerId?: User,
                image?: string) {
        
        if(!revealed) {this.image = "/assets/img/cableBack.jpeg"}
        else if(revealed) {
            if(type == 'BOMB') {this.image = "/assets/img/bomb.jpg"}
            else if(type == 'DIFFUSE') {this.image = "/assets/img/defusedCable.jpg"}
            else if(type == 'BAIT') {this.image = "/assets/img/baitCable.jpg"}
        }
    }
}
