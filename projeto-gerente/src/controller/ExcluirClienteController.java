/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.ExcluirCliente;
import DAO.Conexao;
import model.Cliente;
import java.sql.Connection;
import java.sql.SQLException;
import DAO.ClienteDAO;
import DAO.ContaDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author leandroalencar
 */
public class ExcluirClienteController {
    private ExcluirCliente view;

    public ExcluirClienteController(ExcluirCliente view) {
        this.view = view;
    }

    public void excluirCliente() {
        Cliente cliente = new Cliente();
        String cpf = view.getEntradaExcluirCliente().getText();
        cliente.setCpf(cpf);
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            ClienteDAO dao = new ClienteDAO(conn);
            ContaDAO contaDAO = new ContaDAO(conn);
            boolean excluiuCliente = dao.excluirCliente(cliente);
            boolean excluiuContas = contaDAO.excluirContasPorCPF(cpf);
            
            if (excluiuCliente && excluiuContas) {
                JOptionPane.showMessageDialog(view, "Usuário Excluido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Nenhum Cliente encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
           
            
        } catch(SQLException err) {
            JOptionPane.showMessageDialog(view, "Erro de conexão!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
