/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.Conexao;
import DAO.ContaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Conta;
import view.SaldoCliente;

/**
 *
 * @author leandroalencar
 */
public class ExibirSaldoController {
    private SaldoCliente view;

    /**
     *
     * @param view
     */
    public ExibirSaldoController(SaldoCliente view) {
        this.view = view;
    }
    
    /**
     *
     */
    public void ListarSaldos() {

        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();

            ContaDAO dao = new ContaDAO(conn);
            List<Conta> contas = dao.exibirSaldoContasPorCPFSenha(view.getEntradaCPF().getText(), view.getEntradaSenha().getText());

            if (!contas.isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) view.getjTable1().getModel();
                model.setRowCount(0);
                model.setColumnIdentifiers(new Object[]{"Tipo", "Saldo", "Limite"});
                for (Conta conta : contas) {
                    model.addRow(new Object[]{conta.getTipo(), conta.getSaldo(), conta.getLimite()});
                }

                JOptionPane.showMessageDialog(view, "Contas Listadas!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException err) {
            System.out.println(err);
            JOptionPane.showMessageDialog(view, "Erro de conexão!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
