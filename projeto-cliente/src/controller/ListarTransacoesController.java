/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import view.Extrato;
import DAO.Conexao;
import DAO.ContaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Transacao;
/**
 *
 * @author leandroalencar
 */
public class ListarTransacoesController {
    private Extrato view;

    public ListarTransacoesController(Extrato view) {
        this.view = view;
    }
    
    public void ListarTransacoes() {
        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();

            ContaDAO dao = new ContaDAO(conn);
            List<Transacao> transacoes = dao.listarTransacoes(view.getEntradaCPF().getText(), view.getEntradaSenha().getText());

            if (!transacoes.isEmpty()) {
                DefaultTableModel model = (DefaultTableModel) view.getjTable1().getModel(); // Substitua pelo nome real do seu JTable
                model.setRowCount(0);
                model.setColumnIdentifiers(new Object[]{"CPF", "Tipo Transação", "Tipo Conta", "Valor", "Data Transação"});

                for (Transacao transacao : transacoes) {
                    model.addRow(new Object[]{transacao.getCpf(), transacao.getTipoTransacao(), transacao.getTipoConta(), transacao.getValor(), transacao.getDataTransacao()});
                }

                JOptionPane.showMessageDialog(view, "Transações Listadas!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Nenhuma transação encontrada para este usuário!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException err) {
            System.out.println(err);
            JOptionPane.showMessageDialog(view, "Erro de conexão!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
