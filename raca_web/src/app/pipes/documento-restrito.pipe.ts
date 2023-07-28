import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'docRestrito' })
export class DocumentoRestritoPipe implements PipeTransform {
    transform(value: boolean): string {
      return value == true ? 'Sim' : 'Não';
    }
}
