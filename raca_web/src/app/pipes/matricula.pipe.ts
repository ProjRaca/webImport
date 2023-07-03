import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'matricula' })
export class MatriculaPipe implements PipeTransform {
    transform(value: string|number): string {
      let valorFormatado = value + '';
      valorFormatado = valorFormatado
      .padStart(6, '0')
      return valorFormatado;
    }
}
