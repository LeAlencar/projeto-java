/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author leandroalencar
 */
public class ContaPoupanca extends Conta {

    public ContaPoupanca(String cpf, double saldoInicial) {
        super("poupanca", saldoInicial, cpf);
    }

    @Override
    public boolean sacar(double valor) {
        if (getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            return true;
        }
        return false;
    }
}
