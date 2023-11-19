/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author leandroalencar
 */
public class Transacao {
    private String cpf;
    private String tipoTransacao;
    private double valor;
    private String tipoConta;
    private Date dataTransacao;

    /**
     *
     * @param cpf
     * @param tipoTransacao
     * @param valor
     * @param tipoConta
     * @param dataTransacao
     */
    public Transacao(String cpf, String tipoTransacao, double valor, String tipoConta, Date dataTransacao) {
        this.cpf = cpf;
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.tipoConta = tipoConta;
        this.dataTransacao = dataTransacao;
    }

    /**
     *
     */
    public Transacao() {
    }

    /**
     *
     * @return
     */
    public String getCpf() {
        return cpf;
    }

    /**
     *
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     *
     * @return
     */
    public String getTipoTransacao() {
        return tipoTransacao;
    }

    /**
     *
     * @param tipoTransacao
     */
    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    /**
     *
     * @return
     */
    public double getValor() {
        return valor;
    }

    /**
     *
     * @param valor
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     *
     * @return
     */
    public String getTipoConta() {
        return tipoConta;
    }

    /**
     *
     * @param tipoConta
     */
    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    /**
     *
     * @return
     */
    public Date getDataTransacao() {
        return dataTransacao;
    }

    /**
     *
     * @param dataTransacao
     */
    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
    
    
}
