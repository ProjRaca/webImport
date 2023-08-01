import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'tpFilial' })
export class FilialPipe implements PipeTransform {
    transform(value: string|number): string {
      return value == '2' ? 'Casa de Carnes' : 'Raça Distribuidora';
    }
}
