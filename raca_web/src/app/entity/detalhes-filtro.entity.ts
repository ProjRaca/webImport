import { TipoDocumentoEnum } from "../enums/TipoDocumentoEnum.enum";

export interface DetalhesFiltro{
  competencia: string;
  nota: string;
  vencimento: string;
  empresa: string;
  historico: TipoDocumentoEnum;

}
