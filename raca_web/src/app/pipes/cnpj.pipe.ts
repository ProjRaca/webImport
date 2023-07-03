import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'cnpj' })
export class CnpjPipe implements PipeTransform {
    transform(value: string|number): string {
      let valorFormatado = value + '';
      valorFormatado = valorFormatado
      .padStart(14, '0')                  // item 1
      .substr(0, 14)                      // item 2
      .replace(/[^0-9]/, '')              // item 3
      .replace(                           // item 4
          /(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/,
          '$1.$2.$3/$4-$5'
      );
      return valorFormatado;
    }
}
