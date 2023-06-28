export interface Movimentacao {
    idMovimentacao: number;
    codigoFinal: string;
    cnpjempresa: string;
    idfuncionario: number;
    nomefuncionario: string;
    cpffuncionario: string;
    tipoparceiro: string;
    historico: string;
    valor: number;
    vencimento: Date;
    competencia: Date;
    status: string;
    ultimousuario: string;
    ultimaalteracao: Date;
    atencia: string;
    agenciadv: string;
    concacorrente: string;
    contacorrentedv: string;
}
