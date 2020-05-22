import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'myMatch'
})
export class MyMatchPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    if (value == args[0]) {

      return "#8ecf7e";
    }
  }

}
