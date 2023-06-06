/**
 * @author pedrom
 * Data: 05/08/2022
 */

package com.racape.prjimports.bean;

public class Contas {

    private String cpffuncionario;
    private String nomefuncionario;
    private String idconta;
    private String descricao;

    public String getCpffuncionario() {
        return cpffuncionario;
    }

    public void setCpffuncionario(String cpffuncionario) {
        this.cpffuncionario = cpffuncionario;
    }

    public String getNomefuncionario() {
        return nomefuncionario;
    }

    public void setNomefuncionario(String nomefuncionario) {
        this.nomefuncionario = nomefuncionario;
    }

    public String getIdconta() {
        return idconta;
    }

    public void setIdconta(String idconta) {
        this.idconta = idconta;
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
