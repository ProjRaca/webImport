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
}
