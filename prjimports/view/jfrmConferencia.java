/**
 * @author pedrom
 * Data: 28/07/2022
 */

package com.racape.prjimports.view;

import com.racape.prjimports.bean.Contas;
import com.racape.prjimports.dao.ContasDao;
import com.racape.prjimports.bean.Movimentacao;
import com.racape.prjimports.dao.MovimentacaoDao;
import static com.racape.prjimports.dao.MovimentacaoDao.TransferirMovimentacao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class jfrmConferencia extends javax.swing.JInternalFrame {
    
    public jfrmConferencia() throws Exception {
        initComponents();
        
        ContasDao cdao = new ContasDao();
        List<Contas> lista = cdao.ListarContas();
    
        //this.jcboHistorico.removeAll();
        
        for(Contas C: lista){
            this.jcboHistorico.addItem(C);
        }
        
        DefaultTableModel modelo = (DefaultTableModel) jtblMovimentacao.getModel();
        jtblMovimentacao.setRowSorter(new TableRowSorter(modelo));
        carregaTabela();
    }

    public void carregaTabela() throws Exception{
        DefaultTableModel modelo = (DefaultTableModel) jtblMovimentacao.getModel();
        modelo.setNumRows(0);
        MovimentacaoDao mdao = new  MovimentacaoDao();
        
        for (Movimentacao movimento: mdao.ListarMovimento()){
            modelo.addRow(new Object[]{
                movimento.getIdfuncionario(),
                movimento.getNomefuncionario(),
                movimento.getCpffuncionario(),
                movimento.getContaDebito(),
                movimento.getValor(),
                movimento.getHistorico(),
                movimento.getTipoparceiro()
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jcboHistorico = new javax.swing.JComboBox<>();
        jbtnAtualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jftxtfNota = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jftxtfCompetencia = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jftxtfVencimento = new javax.swing.JFormattedTextField();
        jbtntransferencia = new javax.swing.JButton();
        jcboEmpresa = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblMovimentacao = new javax.swing.JTable();

        setClosable(true);
        setTitle("Conferência ");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Adicionais"));

        jLabel3.setText("Histórico");

        jcboHistorico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar" }));

        jbtnAtualizar.setText("Atualizar");
        jbtnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAtualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nota");

        try {
            jftxtfNota.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("Competência ");

        try {
            jftxtfCompetencia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jftxtfCompetencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jftxtfCompetenciaFocusLost(evt);
            }
        });

        jLabel7.setText("Vencimento");

        try {
            jftxtfVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jbtntransferencia.setText("Transferir Movimentação");
        jbtntransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtntransferenciaActionPerformed(evt);
            }
        });

        jcboEmpresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar", "Casa de Carnes Comércio e Distribuição", "Raça Distribuição e Logística" }));

        jLabel4.setText("Empresa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jftxtfCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(70, 70, 70)
                                .addComponent(jLabel7))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jftxtfNota, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jftxtfVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jcboEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jcboHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnAtualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtntransferencia)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jftxtfCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftxtfNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jftxtfVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcboEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcboHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnAtualizar)
                    .addComponent(jbtntransferencia))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalhes"));
        jPanel1.setToolTipText("");

        jtblMovimentacao.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtblMovimentacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Matrícula", "Colaborador", "CPF", "Conta Débito", "Valor", "Histórico", "Parceiro", "Marcar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblMovimentacao.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(jtblMovimentacao);
        if (jtblMovimentacao.getColumnModel().getColumnCount() > 0) {
            jtblMovimentacao.getColumnModel().getColumn(0).setPreferredWidth(60);
            jtblMovimentacao.getColumnModel().getColumn(1).setPreferredWidth(300);
            jtblMovimentacao.getColumnModel().getColumn(2).setPreferredWidth(100);
            jtblMovimentacao.getColumnModel().getColumn(3).setPreferredWidth(100);
            jtblMovimentacao.getColumnModel().getColumn(4).setPreferredWidth(90);
            jtblMovimentacao.getColumnModel().getColumn(5).setPreferredWidth(150);
            jtblMovimentacao.getColumnModel().getColumn(6).setPreferredWidth(90);
            jtblMovimentacao.getColumnModel().getColumn(7).setPreferredWidth(60);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 975, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAtualizarActionPerformed
        
        String codigofilial = "";
        String nota = "";
        
        MovimentacaoDao mdao = new MovimentacaoDao();
        
        try {
            if(this.jftxtfCompetencia.getText().equals("  /  /    ")){
                JOptionPane.showMessageDialog(null, "Informe o dia, mês e ano da Competência!", "Atenção", HEIGHT, frameIcon);
                return;
            }
            
            if(this.jftxtfNota.getText().equals("  /    ")){
                JOptionPane.showMessageDialog(null, "Informe o mês e ano da Nota!", "Atenção", HEIGHT, frameIcon);
                return;
            }
            
            if(this.jftxtfVencimento.getText().equals("  /  /    ")){
                JOptionPane.showMessageDialog(null, "Informe o dia, mês e ano do Vencimento!", "Atenção", HEIGHT, frameIcon);
                return;
            }
            
            if(this.jcboEmpresa.getSelectedItem().toString().equals("Selecionar")){
                JOptionPane.showMessageDialog(null, "Selecione a Empresa", "Atenção", HEIGHT, frameIcon);
                return;
            }else if(this.jcboEmpresa.getSelectedItem().toString().equals("Raça Distribuição e Logística")){
                codigofilial = "1";
            }else{
                codigofilial = "2"; 
            }
            
            if(this.jcboHistorico.getSelectedItem().toString().equals("Selecionar")){
                JOptionPane.showMessageDialog(null, "Informe o Histórico!", "Atenção", HEIGHT, frameIcon);
                return;
            }
            
            nota = (this.jftxtfNota.getText().substring(0,2) + this.jftxtfNota.getText().substring(3,7));
            
            mdao.AtualizarMovimentacao(codigofilial, this.jcboHistorico.getSelectedItem().toString(), this.jftxtfCompetencia.getText(), nota, this.jftxtfVencimento.getText());
            carregaTabela();
        } catch (Exception ex) {
            Logger.getLogger(jfrmConferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnAtualizarActionPerformed

    private void jftxtfCompetenciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jftxtfCompetenciaFocusLost
        this.jftxtfNota.setText(this.jftxtfCompetencia.getText().substring(3,5) + this.jftxtfCompetencia.getText().substring(6,10));
    }//GEN-LAST:event_jftxtfCompetenciaFocusLost

    private void jbtntransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtntransferenciaActionPerformed
        try {
            // TODO add your handling code here:
            TransferirMovimentacao();
        } catch (Exception ex) {
            Logger.getLogger(jfrmConferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtntransferenciaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnAtualizar;
    private javax.swing.JButton jbtntransferencia;
    private javax.swing.JComboBox<String> jcboEmpresa;
    private javax.swing.JComboBox<Object> jcboHistorico;
    private javax.swing.JFormattedTextField jftxtfCompetencia;
    private javax.swing.JFormattedTextField jftxtfNota;
    private javax.swing.JFormattedTextField jftxtfVencimento;
    private javax.swing.JTable jtblMovimentacao;
    // End of variables declaration//GEN-END:variables

}
