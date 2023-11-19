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
public class ContaSalario extends Conta implements RegrasTarifacao {

    /**
     *
     * @param cpf
     * @param saldoInicial
     */
    public ContaSalario( String cpf, double saldoInicial) {
        super("salario", saldoInicial, cpf);
    }

    /**
     *
     * @param valor
     * @return
     */
    @Override
    public boolean sacar(double valor) {
        if (getSaldo() >= valor) {
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
        double tarifa = valor * 0.05;
        setSaldo(getSaldo() - tarifa);
    }
}
