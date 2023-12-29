/**
 * @author Pedrom
 * Atualizado em: 24/08/2022
 */

package raca.api.config.db;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
@Component
public class MinhaConnectionOracle {
    
   private static final String DRIVER = "oracle.jdbc.OracleDriver";
    
   private static final String USER = "RACA";

   private static final String PASSWORD = "R4CAP3BANC0";
       
   private static final String URL = "jdbc:oracle:thin:@192.168.1.1:1521:WINT";

    //private static final String USER = "TESTE";

    //private static final String PASSWORD = "TESTE";

    //private static final String URL = "jdbc:oracle:thin:@192.168.1.210:1521:TESTE";

    //Cria a Conexão com o banco de dados
    public Connection createConnectionToOracle() throws Exception{
        try{
            // O método forName carrega o tipo de driver a ser reconhecido pela JVM
            Class.forName(DRIVER);

            // Estabelecendo a conexão
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }

    /*
    public static void main(String[] args) throws Exception {
        //Recuperando uma conexao com o banco de dados
        Connection conn = createConnectionToOracle();
        
        //Testar se a conexão é nula
        if(conn != null){
            System.out.println("Conexão obtida com sucesso!");
            conn.close();
        }
    }*/
}
