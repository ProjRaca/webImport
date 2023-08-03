/**
 * @author pedrom
 * Data: 27/07/2022
 */

package raca.api.repository.oracle;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import raca.api.config.db.MinhaConnectionOracle;
import raca.api.domain.entity.postgres.Movimentacao;
import raca.api.repository.postgres.MovimentacaoRepository;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MovimentacaoDao {

    private final MinhaConnectionOracle minhaConnectionOracle;
    private final MovimentacaoRepository movimentacaoRepository;

    public int ConsultarUltimoMovimento() throws Exception{
        int UltimoMovimento = 0;
        String sSql = "SELECT NVL(PROXNUMLANC,1) PROXNUMLANC FROM PCCONSUM";
        Connection connOracle = null;
        connOracle = minhaConnectionOracle.createConnectionToOracle();
        try {
            PreparedStatement pstmtO = connOracle.prepareStatement(sSql);
            ResultSet rs = null;
            rs = pstmtO.executeQuery();
            while(rs.next()){
               UltimoMovimento = rs.getInt("PROXNUMLANC");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            connOracle.close();
        }
         return (UltimoMovimento);
    }
    
    public void AtualizarProximoRegistro(int numreg) throws Exception{
        numreg++;
        String sSql = "UPDATE PCCONSUM SET PROXNUMLANC = " + numreg;
        Connection connOracle = null;
        connOracle = minhaConnectionOracle.createConnectionToOracle();
        try  {
               PreparedStatement pstmtO = connOracle.prepareStatement(sSql);
               pstmtO.executeUpdate();
               pstmtO.executeUpdate();
        }catch(Exception ex) {
            System.out.println("Erro ao Salvar Próximo Registro!");
            ex.printStackTrace();
        }
    }
     private List<Movimentacao> getMovimentacoesStatusP(){
         List<Movimentacao> all = movimentacaoRepository.findAll();
         return all.stream().filter(m -> m.getStatus().equals("P")).collect(Collectors.toList());
     }

    public void TransferirMovimentacaoOracle( List<Movimentacao> listMovimentacao) throws Exception{
         final Connection connOracle = minhaConnectionOracle.createConnectionToOracle();
         try {

             listMovimentacao.stream().forEach(x -> {
                 int numreg = 0;
                 try {
                     numreg = this.ConsultarUltimoMovimento();

                     Date data = new Date();
                     SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                     SimpleDateFormat dateFormatConsulta = new SimpleDateFormat("yyyy-MM-dd");
                     String dataVencimento = dateFormat.format(x.getVencimento());
                     String dataCompetencia = dateFormat.format(x.getCompetencia());
                     String dataCompetenciaConsulta = dateFormatConsulta.format(x.getCompetencia());
                     String dataSistema =  dateFormat.format(data);

                     String oSql = getSql(x, numreg, dataVencimento, dataCompetencia, dataSistema);

                     if(!validaInsert( x.getCodigofilial(), x.getNota().replace("/",""),
                             dataCompetenciaConsulta,x.getIdfuncionario(), x.getContacorrente())){
                         if(x.getTipoparceiro() != null){
                             insertOracle(connOracle, numreg, oSql);
                         }
                     }
                     AtualizarProximoRegistro(numreg);
                 } catch (Exception e) {
                     e.printStackTrace();
                     throw new RuntimeException(e);
                 }

             });
         }catch (Exception e){
            e.printStackTrace();
         }finally {
             connOracle.close();
         }
    }

    private static String getSql(Movimentacao x, int numreg, String dataVencimento, String dataCompetencia, String dataSistema) {
        String oSql = " INSERT INTO PCLANC (RECNUM, DTLANC, HISTORICO, DUPLIC, CODFILIAL, INDICE," +
                " TIPOLANC, TIPOPARCEIRO, NOMEFUNC, MOEDA, NFSERVICO, PARCELA, NUMNOTA," +
                " CODFORNEC, FORNECEDOR, TIPOSERVICO, UTILIZOURATEIOCONTA, PRCRATEIOUTILIZADO, VALOR," +
                " CODCONTA, RECNUMPRINC, DTVENC, DTEMISSAO, DTCOMPETENCIA)" +
                " VALUES (" + numreg + ", '" + dataSistema + "', '" + x.getHistorico() +
                "', '1', '" + x.getCodigofilial() + "', 'A', 'C', '" + x.getTipoparceiro() +
                "', 'INTEGRACAO RH'," +
                " 'R', 'N', '1', " + Integer.parseInt( x.getNota().replace("/","")) + ", " +
                x.getIdfuncionario() + ", '" + x.getNomefuncionario() + "'," +
                " '99', 'N', 100, " + x.getValor() + ", " +
                x.getContacorrente() + ", " + numreg + ", '" + dataVencimento +
                "', '" + dataSistema + "', '" + dataCompetencia + "')";
        return oSql;
    }

    public boolean validaInsert(String codFilial, String numNota, String dataCompetencia, int codFornec, String codConta) throws Exception {
        String sSql = getSqlConsultaValidaInsert();

        Connection connOracle;
        PreparedStatement pstmtO = null;
        ResultSet rs = null;

        connOracle = minhaConnectionOracle.createConnectionToOracle();

        try {
            pstmtO = connOracle.prepareStatement(sSql);
            pstmtO.setString(1, codFilial);
            pstmtO.setString(2, numNota);
            pstmtO.setString(3, dataCompetencia);
            pstmtO.setInt(4, codFornec);
            pstmtO.setString(5, codConta);

            rs = pstmtO.executeQuery();

            while (rs.next()) {
                rs.getString("CODCONTA");
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmtO != null) {
                pstmtO.close();
            }
            if (connOracle != null) {
                connOracle.close();
            }
        }
        return false;
    }

    private void insertOracle(Connection connOracle, int numreg, String oSql) {
        try {
            PreparedStatement pstmtO = connOracle.prepareStatement(oSql);
            pstmtO.execute();
            pstmtO.clearParameters();
            pstmtO.close();
        }catch(Exception ex) {
            Logger.getLogger(MovimentacaoDao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();

        }
    }

    private String getSqlConsultaValidaInsert(String codFilial, String numNota, String dataCompetencia, int codFornec, String codConta){
        String sSql = "SELECT * FROM PCLANC WHERE CODFILIAL = " + codFilial +
                " AND NUMNOTA = " +  numNota +
                " AND DTCOMPETENCIA = '" + dataCompetencia +
                "' AND CODFORNEC = " + codFornec +
                " AND CODCONTA = " + codConta;
        return sSql;
    }

    private String getSqlConsultaValidaInsert() {
        String sSql = "SELECT * FROM PCLANC WHERE CODFILIAL = ? AND NUMNOTA = ? AND DTCOMPETENCIA = TO_DATE(?, 'YYYY-MM-DD') AND CODFORNEC = ? AND CODCONTA = ?";
        return sSql;
    }


}

