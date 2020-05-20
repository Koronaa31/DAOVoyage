import { User } from './user';
import { Card } from './card';

export class Match {

  constructor (public id?: number,
               public name?: string,
               public size?: number,
               public state?: string,
               public winner?: string,
               public owner?: User,
               public current?: User,
               public players?: Array<User>,
               public deck?: Array<Card>) {  }
}