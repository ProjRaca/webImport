/**
 * @author pedrom
 * Data: 05/08/2022
 */

package connections.dao;

import com.racape.prjimports.bean.Contas;
import connections.minhaConnectionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContasDao {
   
    public List<Contas> ListarContas() throws Exception{
        String sSql = "SELECT DISTINCT(descricao) FROM tblcontas order by descricao desc";
        Connection conn = null;
        
        //Criar a conexão com o banco de dados
        conn = minhaConnectionMysql.createConnectionToMySQL();

        List<Contas> listarContas = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sSql)) {
            ResultSet rs = null;
            rs = pstmt.executeQuery();

            while (rs.next()){
                Contas conta = new Contas();
                //conta.setCpffuncionario(rs.getString("cpffuncionario"));
                //conta.setNomefuncionario(rs.getString("nomefuncionario"));
                //conta.setIdconta(rs.getString("idconta"));
                conta.setDescricao(rs.getString("descricao"));
                                
                listarContas.add(conta);
            }
        }catch(SQLException ex){
            Logger.getLogger(HistoricoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            //implementar o metodo closeconnection
        }   
        return listarContas;
    }
}
