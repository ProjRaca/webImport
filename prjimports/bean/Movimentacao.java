/**
 * @author pedrom
 * Data: 27/07/2022
 */
package com.racape.prjimports.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Movimentacao {
    private int idmovimentacao;
    private String codigofilial;
    private String cnpjempresa;
    private int idfuncionario;
    private String nomefuncionario;
    private String cpffuncionario;
    private String tipoparceiro;
    private String contaDebito;
    private String historico;
    private BigDecimal valor;
    private Date vencimento;
    private Date competencia;
    private String status;
    private String ultimoUsuario;
    private Date ultimaAlteracao;

    public int getIdmovimentacao() {
        return idmovimentacao;
    }

    public void setIdmovimentacao(int idmovimentacao) {
        this.idmovimentacao = idmovimentacao;
    }

    public String getIdempresa() {
        return codigofilial;
    }

    public void setIdempresa(String idempresa) {
        this.codigofilial = idempresa;
    }
     
    public String getCnpjempresa() {
        return cnpjempresa;
    }

    public void setCnpjempresa(String cnpjempresa) {
        this.cnpjempresa = cnpjempresa;
    }

    public int getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(int idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getNomefuncionario() {
        return nomefuncionario;
    }

    public void setNomefuncionario(String nomefuncionario) {
        this.nomefuncionario = nomefuncionario;
    }

    public String getCpffuncionario() {
        return cpffuncionario;
    }

    public void setCpffuncionario(String cpffuncionario) {
        this.cpffuncionario = cpffuncionario;
    }

    public String getTipoparceiro() {
        return tipoparceiro;
    }

    public void setTipoparceiro(String tipoparceiro) {
        this.tipoparceiro = tipoparceiro;
    }

    public String getContaDebito() {
        return contaDebito;
    }

    public void setContaDebito(String contaDebito) {
        this.contaDebito = contaDebito;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataVencimento() {
        return vencimento;
    }

    public void setDataVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Date getCompetencia() {
        return competencia;
    }

    public void setCompetencia(Date competencia) {
        this.competencia = competencia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUltimoUsuario() {
        return ultimoUsuario;
    }

    public void setUltimoUsuario(String ultimoUsuario) {
        this.ultimoUsuario = ultimoUsuario;
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }
    
}
