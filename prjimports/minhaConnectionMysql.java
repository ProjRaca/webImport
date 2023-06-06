/**
 * @author Pedrom
 * Atualizado em: 14/07/2022
 */

package connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class minhaConnectionMysql {
    
    private static final String USER = "root";
    
    private static final String PASSWORD = "admserver01";
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
     
    private static final String URL = "jdbc:mysql://localhost:3306/dbfinanceiro";

    //Cria a Conexão com o banco de dados
    public static Connection createConnectionToMySQL() throws Exception{
        
        // O método forName carrega o tipo de driver a ser reconhecido pela JVM
        Class.forName(DRIVER);

        // Estabelecendo a conexão
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }
    
    public static void main(String[] args) throws Exception {
        //Recuperando uma conexao com o banco de dados
        Connection conn = createConnectionToMySQL();
        
        //Testar se a conexão é nula
        if(conn != null){
            System.out.println("Conexão obtida com sucesso!");
            conn.close();
        }
    }
}
