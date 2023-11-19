/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author leandroalencar
 */
public class SaldoELimite {
    private double saldo;
    private double limite;
    
    /**
     *
     * @param saldo
     * @param limite
     */
    public SaldoELimite(double saldo, double limite) {
        this.saldo = saldo;
        this.limite = limite;
    }

    /**
     *
     * @return
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     *
     * @return
     */
    public double getLimite() {
        return limite;
    }
}
