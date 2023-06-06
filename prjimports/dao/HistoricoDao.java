/**
 * @author pedrom
 * Data: 27/07/2022
 */

package com.racape.prjimports.dao;

import com.racape.prjimports.bean.Historico;
import connections.minhaConnectionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedrom
 */
public class HistoricoDao {
    
    public List<Historico> ListarHistorico() throws Exception{
        String sSql = "SELECT distinct(descricao) FROM tblcontas";
        Connection conn = null;
        
        //Criar a conexão com o banco de dados
        conn = minhaConnectionMysql.createConnectionToMySQL();

        List<Historico> listarHistorico = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sSql)) {
            ResultSet rs = null;
            rs = pstmt.executeQuery();

            while (rs.next()){
                Historico hist = new Historico();
                //hist.setIdHistorico(rs.getInt("idhistorico"));
                hist.setDescricao(rs.getString("descricao"));
                                
                listarHistorico.add(hist);
            }
        }catch(SQLException ex){
            Logger.getLogger(HistoricoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            //implementar o metodo closeconnection
        }   
        return listarHistorico;
    }
}
