import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'tpUsuario' })
export class UsuarioPipe implements PipeTransform {
    transform(value: boolean): string {
      return value == true ? 'Administrador' : 'Usuário';
    }
}
