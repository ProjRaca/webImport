import { TipoDocumentoEnum } from "../enums/TipoDocumentoEnum.enum";

export  interface DocumentoDetalhes {
  id?: number;
  matricula: string;
  colaborador: string;
  cpf: string;
  conta_debito: string;
  valor: string;
  historico: string;
  parceiro: string;
  marca: string;
}
