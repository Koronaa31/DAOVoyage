import { Match } from './match';
import { User } from './user';

export class Card {
    constructor(public id?: number, public revealed?: boolean, public typeRevealed?: string, private match?: Match, private owner?: User) {}
}
