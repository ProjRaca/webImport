export enum TipoDocumentoEnum {
    NFE = 'NF-e',
    NFS = 'NFS_SE',
    BOLETO ='Boleto',
    CTE = 'CTE',
    MDFE = 'MDFE',
    BORDERO = 'Bordero',
    CARREGAMENTO = 'Carregamento',
    CONTRATO_SERVICO ='Contrato Servico',
    CONTRATO_ALUGUEL = 'Contrato Aluguel'
  }

  export function convertEnumToArray(){
        const arrayObjects = []
          // Retrieve key and values using Object.entries() method.
          for (const [propertyKey, propertyValue] of Object.entries(TipoDocumentoEnum)) {

           // Ignore keys that are not numbers
           if (!Number.isNaN(Number(propertyKey))) {
             continue;
           }

           // Add keys and values to array
           arrayObjects.push({ id: propertyValue, name: propertyKey });
         }


     }
