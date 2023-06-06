/*
 * Autor: Pedro Morais
 * Data: 26/07/2022
 */

package com.racape.prjimports.view;

import com.racape.prjimports.bean.ModeloDados;
import com.racape.prjimports.dao.CarregaDadosDao;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class jfrmImportacao extends javax.swing.JInternalFrame {

    public jfrmImportacao() throws Exception {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jtxtDiretorio = new javax.swing.JTextField();
        jbtnSelecionar = new javax.swing.JButton();
        jbtnImportar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Importação do Arquivo Excel");
        setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jLabel2.setText("Selecione o arquivo:");

        jbtnSelecionar.setText("Selecionar");
        jbtnSelecionar.setName(""); // NOI18N
        jbtnSelecionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSelecionarActionPerformed(evt);
            }
        });

        jbtnImportar.setText("Importar Dados");
        jbtnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnImportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtxtDiretorio, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnSelecionar))
                    .addComponent(jbtnImportar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtDiretorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSelecionar))
                .addGap(27, 27, 27)
                .addComponent(jbtnImportar)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSelecionarActionPerformed

        JFileChooser caminho = new JFileChooser();
        //caminho.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        caminho.showOpenDialog(null);
        File arquivo = caminho.getSelectedFile();
        String nomearquivo = arquivo.getAbsolutePath();
        jtxtDiretorio.setText(nomearquivo);
    }//GEN-LAST:event_jbtnSelecionarActionPerformed

    private void jbtnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnImportarActionPerformed

        if (this.jtxtDiretorio.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Informe o local onde se encontra o arquivo!","Atenção",JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                CarregaDadosDao carregadados = new CarregaDadosDao();
                
                List<ModeloDados> func = carregadados.criar(this.jtxtDiretorio.getText());
                                
                //carregadados.imprimir(func);
                carregadados.inserirMovimentacao(func);

            }catch(IOException e){
                JOptionPane.showMessageDialog(null,"Erro ao Tentar Importar a Movimentação!","Atenção",JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(jfrmConferencia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jbtnImportarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbtnImportar;
    private javax.swing.JButton jbtnSelecionar;
    private javax.swing.JTextField jtxtDiretorio;
    // End of variables declaration//GEN-END:variables
}
