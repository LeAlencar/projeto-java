/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.ContaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.ContaSalario;
import model.SaldoELimite;
import view.DebitarCliente;

/**
 *
 * @author leandroalencar
 */
public class DebitarClienteController {

    private DebitarCliente view;

    /**
     *
     * @param view
     */
    public DebitarClienteController(DebitarCliente view) {
        this.view = view;
    }

    /**
     *
     */
    public void debitarValor() {
        Conexao conexao = new Conexao();
        int tipoContaSelecionada = view.getTipoContaSelecionada();
        Conta conta;
        String tipoConta;
        String cpf = view.getEntradaCPF().getText();
        String senha = view.getEntradaSenha().getText();
        double valor = Double.parseDouble(view.getEntradaValor().getText());

        try {
            Connection conn = conexao.getConnection();

            ContaDAO dao = new ContaDAO(conn);
            SaldoELimite saldoELimite = dao.obterSaldoPorCPFeConta(cpf, "corrente");

            switch (tipoContaSelecionada) {
                case 1 -> {
                    tipoConta = "corrente";
                    conta = new ContaCorrente(cpf, saldoELimite.getSaldo(), saldoELimite.getLimite());
                }
                case 2 -> {
                    tipoConta = "salario";
                    conta = new ContaSalario(cpf, saldoELimite.getSaldo());
                }
                case 3 -> {
                    tipoConta = "poupanca";
                    conta = new ContaPoupanca(cpf, saldoELimite.getSaldo());
                }
                default ->
                    throw new IllegalStateException("Tipo de conta desconhecido: ");
            }

            conta.sacar(valor);
            dao.debitarValor(cpf, senha, tipoConta, valor, conta.getSaldo());
            JOptionPane.showMessageDialog(view, "Valor debitado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException err) {
            System.out.println(err);
            JOptionPane.showMessageDialog(view, "Erro de conexão!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
