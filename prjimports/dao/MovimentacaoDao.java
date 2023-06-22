/**
 * @author pedrom
 * Data: 27/07/2022
 */

package connections.dao;

import com.racape.prjimports.bean.Movimentacao;
import connections.minhaConnectionMysql;
import connections.minhaConnectionOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;

public class MovimentacaoDao {
    
    public static int ConsultarUltimoMovimento(int UltimoMovimento) throws Exception{
        
        String sSql = "SELECT NVL(PROXNUMLANC,1) PROXNUMLANC FROM PCCONSUM";
        
        Connection connOracle = null;
        
        connOracle = minhaConnectionOracle.createConnectionToOracle();
        
        try (PreparedStatement pstmtO = connOracle.prepareStatement(sSql)) {
            ResultSet rs = null;
            rs = pstmtO.executeQuery();
           
            while(rs.next()){
               UltimoMovimento = rs.getInt("PROXNUMLANC");
            }   
                  
        }catch(Exception ex){
            
        }finally{
            connOracle.close();
        }
        
         return (UltimoMovimento);
    }
    
    public static void AtualizarProximoRegistro(int numreg) throws Exception{
        numreg++;
        String sSql = "UPDATE PCCONSUM SET PROXNUMLANC = " + numreg;
        
        Connection connOracle = null;
        
        connOracle = minhaConnectionOracle.createConnectionToOracle();
        
        try (PreparedStatement pstmtO = connOracle.prepareStatement(sSql)) {
            pstmtO.executeUpdate();   
               pstmtO.executeUpdate();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Erro ao Salvar Próximo Registro!");
        }
        //return 1;
    }
    
    public List<Movimentacao> ListarMovimento() throws Exception{
        String sSql = "SELECT idfuncionario, nomefuncionario, cpffuncionario, tipoparceiro, contadebito, valor, " +
                "historico FROM tblmovimentacao WHERE status<>'T' or status is null";
        
        Connection conn = null;
        
        //Criar a conexão com o banco de dados
        conn = minhaConnectionMysql.createConnectionToMySQL();

        List<Movimentacao> listarMovimentacao = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sSql)) {
            ResultSet rs = null;
            rs = pstmt.executeQuery();

            while (rs.next()){
                Movimentacao movimento = new Movimentacao();
                movimento.setIdfuncionario(rs.getInt("idfuncionario")); 
                movimento.setNomefuncionario(rs.getString("nomefuncionario"));
                movimento.setCpffuncionario(rs.getString("cpffuncionario"));
                movimento.setTipoparceiro(rs.getString("tipoparceiro"));
                movimento.setContaDebito(rs.getString("contadebito"));
                movimento.setValor(rs.getBigDecimal("valor"));
                movimento.setHistorico(rs.getString("historico"));
                
                listarMovimentacao.add(movimento);
            }
        }catch(SQLException ex){
            Logger.getLogger(MovimentacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            //implementar o metodo closeconnection
        }   
        return listarMovimentacao;
    }
 
    public void AtualizarMovimentacao(String codigofilial, String historico, String competencia, String nota, String vencimento) throws Exception{
        
        String novovencimento = vencimento.substring(6,10) + "-" +
                                vencimento.substring(3,5) + "-" + 
                                vencimento.substring(0,2);   
       
        String novacompetencia = competencia.substring(6,10) + "-" +
                                 competencia.substring(3,5) + "-" + 
                                 competencia.substring(0,2);
        
        String histcompetencia = competencia.substring(3,5) + "/" + competencia.substring(6,10);
        
        String sSql = "UPDATE tblmovimentacao m SET m.codigofilial = '" + codigofilial + "'," +
                      " m.idfuncionario = (select tblcontas.matriculafuncionario" +
                      " from tblcontas where " +
                      " 1=1 and" +
                      " tblcontas.cpffuncionario = m.cpffuncionario and" +
                      " tblcontas.descricao like '" + historico + "')," +
                      " m.tipoparceiro = (select tblcontas.tipoparceiro" +
                      " from tblcontas where " +
                      " 1=1 and" +
                      " tblcontas.cpffuncionario = m.cpffuncionario and" + 
                      " tblcontas.descricao like '" + historico + "')," + 
                      " m.historico = '" + historico + " - Compet: " + histcompetencia + "'," + 
                      " m.competencia = '" + novacompetencia + "'," + " m.nota = '" + nota + "'," +
                      " m.vencimento = '" +  novovencimento + "'," + " m.status = 'A', " +                                
                      " m.contadebito = (select tblcontas.idconta" +
                      " from tblcontas where " +
                      " 1=1 and" +
                      " tblcontas.cpffuncionario = m.cpffuncionario  and" +
                      " tblcontas.descricao like '" + historico + "')" +
                      " WHERE m.status = '' or m.status is null";
         
        Connection conn = null;
        
        try{
            //Criar a conexão com o banco de dados
            conn = minhaConnectionMysql.createConnectionToMySQL();
            try (PreparedStatement pstmt = conn.prepareStatement(sSql)) {
                pstmt.executeUpdate();
                pstmt.clearParameters(); 
               
                pstmt.close();
                conn.close();
                JOptionPane.showMessageDialog(null,"Atualização Realizada com Sucesso!","Conclusão ",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao Tentar Importar a Movimentação ou já Realizada!","Atenção",JOptionPane.ERROR_MESSAGE);
        }
    }  
    public static void AtualizarTransferencia(String Filial, String idFuncionario, String ContaDebito) throws Exception{
        String sSql = "UPDATE tblmovimentacao m SET m.status = 'T' WHERE m.codigofilial = '" + Filial + "' AND" +
                      " m.idfuncionario = '" + idFuncionario + "' AND" +
                      " m.contadebito = '" + ContaDebito + "'";
        
        Connection conn = null;
        
         try{
            //Criar a conexão com o banco de dados
            conn = minhaConnectionMysql.createConnectionToMySQL();
            try (PreparedStatement pstmt = conn.prepareStatement(sSql)) {
                pstmt.executeUpdate();
                pstmt.clearParameters(); 
               
                pstmt.close();
                conn.close();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao Modificar o Status da Transferência!","Atenção",JOptionPane.ERROR_MESSAGE);
        }
       
    } 
    
    public static void TransferirMovimentacao() throws Exception{
        
        int recnum = 0;
        String mSql = "SELECT * FROM tblmovimentacao WHERE status = 'A'";
        
        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date();
        String dataSistema =  formatador.format(data);
        
        Connection connMysql = null;
        Connection connOracle = null;
        
        //Criar a conexão com o banco de dados
        connMysql = minhaConnectionMysql.createConnectionToMySQL();
        connOracle = minhaConnectionOracle.createConnectionToOracle();
        
        try (PreparedStatement pstmtM = connMysql.prepareStatement(mSql)) {
            ResultSet rs = null;
            rs = pstmtM.executeQuery();
  
            // verificar se o rs é diferente de null
            while (rs.next()){
            
                int numreg = MovimentacaoDao.ConsultarUltimoMovimento(recnum);
                               
                String dataVencimento = rs.getString("vencimento").substring(8,10) + "-" +
                                        rs.getString("vencimento").substring(5,7) + "-" + 
                                        rs.getString("vencimento").substring(0,4);
                      
                String dataCompetencia = rs.getString("competencia").substring(8,10) + "-" +
                                         rs.getString("competencia").substring(5,7) + "-" + 
                                         rs.getString("competencia").substring(0,4);
                
                String oSql = " INSERT INTO PCLANC (RECNUM, DTLANC, HISTORICO, DUPLIC, CODFILIAL, INDICE," +
                              " TIPOLANC, TIPOPARCEIRO, NOMEFUNC, MOEDA, NFSERVICO, PARCELA, NUMNOTA," +
                              " CODFORNEC, FORNECEDOR, TIPOSERVICO, UTILIZOURATEIOCONTA, PRCRATEIOUTILIZADO, VALOR," +
                              " CODCONTA, RECNUMPRINC, DTVENC, DTEMISSAO, DTCOMPETENCIA)" +
                              " VALUES (" + numreg + ", '" +  dataSistema + "', '" + rs.getString("historico") + 
                              "', '1', '" + rs.getString("codigofilial") + "', 'A', 'C', '" + rs.getString("tipoparceiro") +
                              "', 'INTEGRACAO RH'," +
                              " 'R', 'N', '1', " + Integer.parseInt(rs.getString("nota")) + ", " + 
                              rs.getString("idfuncionario") + ", '" + rs.getString("nomefuncionario") + "'," +
                              " '99', 'N', 100, " + rs.getString("valor") + ", " +
                              rs.getString("contadebito") + ", " + numreg + ", '" + dataVencimento +
                              "', '" +  dataSistema + "', '" + dataCompetencia + "')";
                
                try (PreparedStatement pstmtO = connOracle.prepareStatement(oSql)) {    
                    pstmtO.execute();
                    AtualizarTransferencia(rs.getString("codigofilial"), rs.getString("idfuncionario"), rs.getString("contadebito"));
                    pstmtO.clearParameters();
                    AtualizarProximoRegistro(numreg);
                    pstmtO.close();
                    
                }catch(SQLException ex){
                        Logger.getLogger(MovimentacaoDao.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null,"Erro ao Tentar Transferir Movimentação!","Transferência não Realizada",JOptionPane.INFORMATION_MESSAGE);
                }   
            }    
                connOracle.close();
                //JOptionPane.showMessageDialog(null,"vimentação Transferida com com Sucesso!","Conclusão ",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException ex){
            Logger.getLogger(MovimentacaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            //implementar o metodo closeconnection
            connMysql.close();
        } 
     }    
}

