/**
 * @author pedrom
 * Data: 05-09-2022
 */
package connections.util;

public class ConverterData {
   
    public static String getMes(int m) {
        
        String mes[] = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
            return(mes[m-1]); 
    }        
}
