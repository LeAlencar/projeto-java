/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import Interfaces.RegrasTarifacao;

/**
 *
 * @author leandroalencar
 */
public class ContaCorrente extends Conta implements RegrasTarifacao {

    private double limite;

    /**
     *
     * @param cpf
     * @param saldoInicial
     * @param limite
     */
    public ContaCorrente(String cpf, double saldoInicial, double limite) {
        super("corrente", saldoInicial, cpf);
        this.limite = limite;
    }

    /**
     *
     * @param valor
     * @return
     */
    @Override
    public boolean sacar(double valor) {
        if (getSaldo() - valor >= -limite) {
            aplicarTarifa(valor);
            setSaldo(getSaldo() - valor);
            return true;
        }
        return false;
    }

    /**
     *
     * @param valor
     */
    @Override
    public void aplicarTarifa(double valor) {
        double tarifa = valor * 0.01;
        setSaldo(getSaldo() - tarifa);
    }

    // Getters e setters para limite

    /**
     *
     * @return
     */
    public double getLimite() {
        return limite;
    }

    /**
     *
     * @param limite
     */
    public void setLimite(double limite) {
        this.limite = limite;
    }
}
