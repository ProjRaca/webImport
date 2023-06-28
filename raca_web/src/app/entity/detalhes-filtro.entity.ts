import { HistoricoEnum } from "../enums/historicoEnum.enum";

export interface DetalhesFiltro{
  competencia: string;
  nota: string;
  vencimento: string;
  empresa: string;
  historico: HistoricoEnum;

}
