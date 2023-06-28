import { Movimentacao } from "./movimentacao.entity";

export interface MovimentacaoDTO{
  codigofilial: string;
  historico: string;
  historicoDescricao: string;
  vencimento: Date;
  competencia: Date;
  listMovimentacao: Movimentacao[];
}
