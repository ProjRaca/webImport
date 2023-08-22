import { format } from 'date-fns';
export class DataUtils {

  /**
   *  recebe uma string com dia mes e ano juntos (12102023) e devolve 12/10/2023
   * @param date format param 12102023
   */
   public static convertDataStringToPtBrFormat(date: string): string{
      let dia = date.substring(0,2);
      let mes = date.substring(2,4);
      let ano = date.substring(4,8);
      return `${ano}-${mes}-${dia}`;
    }

     /**
   *  recebe uma string com dia mes e ano juntos (12102023) e devolve 12/10/2023
   * @param date format param 12102023
   */
   public static convertNotaStringToPtBrFormat(date: string): string{

    let mes = date.substring(0,2);
    let ano = date.substring(2,8);
    return `${mes}/${ano}`;

  }

  public static formatarData(dataOriginal: string){
    const dataObj = new Date(dataOriginal);
    return format(dataObj, 'dd/MM/yyyy');
  }

    /**
   *  recebe uma string com dia mes e ano 12/10/2023 e devolve juntos (12102023)
   * @param date format param 12102023
   */
    public static convertDataStringToReversePtBrFormat(date: string): string{
      if(date.length > 0 && date != undefined){
        let ano = date.substring(0,4);
        let mes = date.substring(5,7);
        let dia = date.substring(8,10);
        return `${dia}${mes}${ano}`;
      }
      return '';
    }

    /**
     *
     * @param dataOriginal Recebe um date e retorna uma string no formato yyyy/MM/dd
     * @returns
     */
    public static formatarDatetoUsFormat(dataOriginal: Date){
      const dataObj = new Date(dataOriginal);
      return format(dataObj, 'yyyy-MM-dd');
    }

     /**
     *
     * @param dataOriginal Recebe um date e retorna uma string no formato yyyy/MM/dd
     * @returns
     */
     public static formatarDatetoBrFormat(dataOriginal: string | undefined): Date | 'undefined'{
      if (dataOriginal != undefined){
        return  new Date(dataOriginal);
        //return format(dataObj, 'dd-MM-yyyy');
      }else
      return 'undefined'
    }
}
