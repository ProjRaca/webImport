/*************************
    * Data: 19/07/2022
    * Pedrom - Método para inserir as informações no banco de dados 
**************************/

package com.racape.prjimports.dao;

import com.racape.prjimports.bean.ModeloDados;

import connections.minhaConnectionMysql;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import lombok.Cleanup;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

public class CarregaDadosDao{
    
    public List<ModeloDados> criar(String local) throws IOException{
        
        List<ModeloDados> dados = new ArrayList<>();
        
        //Pega o caminho do arquivo 
        @Cleanup FileInputStream caminho = new FileInputStream(local);
        //FileInputStream caminho = new FileInputStream(local);
        HSSFWorkbook arquivo = new HSSFWorkbook(caminho);
        
        //Inicia na aba 1 da planilha
        Sheet planilha = arquivo.getSheetAt(0);

        //Inicia a carregar as linhas novo modelo
        List<Row> linhas = (List<Row>) toList(planilha.iterator());
        
        linhas.forEach(row ->{
            
            //percorrendo as celulas e pegando o conteúdo
            List<Cell> celula = (List<Cell>) toList(row.cellIterator());
                
                ModeloDados func = ModeloDados.builder()
                    .funcionario(celula.get(1).getStringCellValue())
                    .cpf(celula.get(2).getStringCellValue())
                    .valor(new BigDecimal(celula.get(5).getNumericCellValue()))
                    .empresa(celula.get(7).getStringCellValue())
                .build();
                dados.add(func);
        });
        return dados;
    }
    
    public List<?> toList(Iterator<?> iterator){
        return IteratorUtils.toList(iterator);
    }
    
   // public void imprimir (List<ModeloDados> funcionario){
   //     funcionario.forEach(System.out::println);
   // }   
    
    public void inserirMovimentacao(List<ModeloDados> funcionario) throws Exception{
       
        String sSql = "INSERT INTO tblmovimentacao (nomefuncionario, cpffuncionario," +
                      " valor, cnpjempresa, ultimaAlteracao) VALUES " +
                      "( ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        
       // String sSql = "INSERT INTO tblmovimentacao (nomefuncionario, cpffuncionario)" +
        //              //" valor, cnpjempresa, ultimaAlteracao) VALUES " +
        //              " values ( ?, ?)";
        
        Connection conn = null;
        
        try{
            //Criar a conexão com o banco de dados
            conn = minhaConnectionMysql.createConnectionToMySQL();
            try (PreparedStatement pstmt = conn.prepareStatement(sSql)) {
                           
                for (int i = 0; i < funcionario.size(); i++){
                    
                    pstmt.setString(1, funcionario.get(i).getFuncionario());
                    pstmt.setString(2, funcionario.get(i).getCpf());
                    pstmt.setBigDecimal(3, funcionario.get(i).getValor());
                    pstmt.setString(4, funcionario.get(i).getEmpresa());
                    pstmt.execute();
                    pstmt.clearParameters(); 
                }
                pstmt.close();
                conn.close();
                JOptionPane.showMessageDialog(null,"Movimentação Importada com Sucesso!","Conclusão ",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao Tentar Importar a Movimentação ou já Realizada!","Atenção",JOptionPane.ERROR_MESSAGE);
        } 
    }
      
}

