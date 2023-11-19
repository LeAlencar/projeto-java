package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import DAO.Conexao;
import DAO.ContaDAO;
import model.Conta;
import view.ListarClientes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author leandroalencar
 */
public class ListarClientesController {
    private ListarClientes view;

    /**
     *
     * @param view
     */
    public ListarClientesController(ListarClientes view) {
        this.view = view;
    }

    /**
     *
     */
    public void ListarClientes() {

        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();

            ContaDAO dao = new ContaDAO(conn);
            List<Conta> contas = dao.exibirTodasContas();

            if (!contas.isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) view.getContasTable().getModel();
                model.setRowCount(0);
                model.setColumnIdentifiers(new Object[]{"CPF", "Tipo Conta", "Saldo", "Limite"});
                for (Conta conta : contas) {
                    model.addRow(new Object[]{conta.getCpf(), conta.getTipo(), conta.getSaldo(), conta.getLimite()});
                }

                JOptionPane.showMessageDialog(view, "Contas Listadas!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Nenhuma conta cadastrada!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(view, "Erro de conexão!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
