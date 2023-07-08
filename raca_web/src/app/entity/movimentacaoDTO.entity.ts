import { Movimentacao } from "./movimentacao.entity";

export interface MovimentacaoDTO {
  codigofilial: string,
  competencia: string,
  vencimento: string
  nota: string,
  historico: string,
  historicoDescricao?: string,
  listMovimentacao: Movimentacao[]
}
