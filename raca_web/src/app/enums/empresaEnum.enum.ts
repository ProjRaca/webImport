export enum EmpresaEnum {
  CASA_DE_CARNES = "Casa de Carnes",
  RACA_DISTRIBUIDORA = " Raça Distribuidora"
}


export const EmpresaList: {
  key: string;
  value: string;
}[] = Object.entries(EmpresaEnum)
  .map(([key, value]) => ({ key, value }));
