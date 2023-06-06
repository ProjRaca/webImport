/**
 * @author pedrom
 * Data: 24/08/2022
 */

        
package com.racape.prjimports.util;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class Posicionarformulario {
   
    public void carregarFormulario(JInternalFrame janela, JDesktopPane desktop){
        
        int lDesk = desktop.getWidth();
        int aDesk = desktop.getHeight();
        int lIFrame = janela.getWidth();
        int aIFrame = janela.getHeight();
        
        janela.setLocation(lDesk / 2 - lIFrame / 2 , aDesk /2 - aIFrame / 2 );
        desktop.add(janela);
        janela.setVisible(true);
    }  
}
