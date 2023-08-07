export interface DocumentoDTO{
  id?: number,
  iddocpai?: number,
  filial?: string,
  datadocumentesc?: string,
  datavalidade?: string,
  emissor?: string
  tipodocumento?: string,
  documento?: string[],
  restrito?: boolean,
  nome?: string
}
