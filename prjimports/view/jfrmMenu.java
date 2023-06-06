/**
 * @author pedrom
 * @data: 26/07/2022
 */

package com.racape.prjimports.view;

import com.racape.prjimports.util.Posicionarformulario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class jfrmMenu extends javax.swing.JFrame {

    Posicionarformulario frm = new Posicionarformulario();
    
    public jfrmMenu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuImportacao = new javax.swing.JMenuItem();
        jMenuConferencia = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuSair = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bem Vindo!");
        setName("jfrmMenu"); // NOI18N

        jDesktop.setName("jDesktop"); // NOI18N

        javax.swing.GroupLayout jDesktopLayout = new javax.swing.GroupLayout(jDesktop);
        jDesktop.setLayout(jDesktopLayout);
        jDesktopLayout.setHorizontalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 707, Short.MAX_VALUE)
        );
        jDesktopLayout.setVerticalGroup(
            jDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 529, Short.MAX_VALUE)
        );

        jMenu1.setText("Manutenção");
        jMenu1.setBorderPainted(false);

        jMenuImportacao.setText("Importações");
        jMenuImportacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuImportacaoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuImportacao);

        jMenuConferencia.setText("Conferência");
        jMenuConferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuConferenciaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuConferencia);
        jMenu1.add(jSeparator1);

        jMenuSair.setText("Sair");
        jMenu1.add(jMenuSair);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Usuários");

        jMenuItem3.setText("Cadastro");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktop)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktop)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Seja Bem Vindo!");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuImportacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuImportacaoActionPerformed
        jfrmImportacao jfrmI = null;
        try {
            frm.carregarFormulario(jfrmI = new jfrmImportacao(), jDesktop);
        } catch (Exception ex) {
            Logger.getLogger(jfrmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuImportacaoActionPerformed

    private void jMenuConferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuConferenciaActionPerformed
        jfrmConferencia jfrmC;
        jfrmC = null;
        try {
            frm.carregarFormulario(jfrmC = new jfrmConferencia(), jDesktop);
        } catch (Exception ex) {
            Logger.getLogger(jfrmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuConferenciaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jfrmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuConferencia;
    private javax.swing.JMenuItem jMenuImportacao;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuSair;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
