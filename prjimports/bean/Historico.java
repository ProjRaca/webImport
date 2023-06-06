/**
 * @author pedrom
 * Data: 01/08/2022
 */

package com.racape.prjimports.bean;

public class Historico {
    
    private int idhistorico;
    private String descricao;

    public int getIdHistorico() {
        return idhistorico;
    }

    public void setIdHistorico(int idhistorico) {
        this.idhistorico = idhistorico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return this.getDescricao();
    }
}
