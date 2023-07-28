import { Pipe, PipeTransform } from '@angular/core';
import { TipoDocumentoEnum } from '../enums/TipoDocumentoEnum.enum';

@Pipe({ name: 'tpDocumento' })
export class DocumentoPipe implements PipeTransform {
    transform(value: string|number): string {
      let retorno = '';
      switch (value) {
        case "1":
          retorno = TipoDocumentoEnum.BOLETO.valueOf()
           break;
        case "2":
          retorno = TipoDocumentoEnum.BORDERO.valueOf()
          break;
        case "3":
          retorno = TipoDocumentoEnum.CARREGAMENTO.valueOf()
          break;
        case "4":
          retorno = TipoDocumentoEnum.CTE.valueOf()
          break;
        case "5":
          retorno = TipoDocumentoEnum.CONTRATO_ALUGUEL.valueOf()
          break;
        case "6":
          retorno = TipoDocumentoEnum.CONTRATO_SERVICO.valueOf()
          break;
        case "7":
          retorno = TipoDocumentoEnum.MDFE.valueOf()
          break;
        case "8":
          retorno = TipoDocumentoEnum.NFE.valueOf()
          break;
        case "9":
          retorno = TipoDocumentoEnum.NFS.valueOf()
          break;
      }
      return retorno;
    }
}
