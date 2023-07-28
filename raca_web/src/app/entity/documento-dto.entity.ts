export interface DocumentoDTO{
  id?: number,
  iddocpai?: number,
  filial?: string,
  datadocumentesc?: string,
  datavalidade?: string,
  emissor?: string
  tipoDocumento?: string,
  documento?: string[],
  restrito?: boolean,
  nome?: string
}
