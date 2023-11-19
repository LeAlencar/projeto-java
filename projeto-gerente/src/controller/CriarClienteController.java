package controller;

import DAO.Conexao;
import view.CriarCliente;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.ContaSalario;
import java.sql.Connection;
import java.sql.SQLException;
import DAO.ClienteDAO;
import DAO.ContaDAO;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author leandroalencar
 */
public class CriarClienteController {

    private CriarCliente view;

    /**
     *
     * @param view
     */
    public CriarClienteController(CriarCliente view) {
        this.view = view;
    }

    /**
     *
     */
    public void CriarCliente() {
        Cliente cliente = new Cliente(view.getEntradaNome().getText(), view.getEntradaSenha().getText(), view.getEntradaCPF().getText());
        int tipoConta = view.getTipoContaSelecionada();
        Conta conta;

        switch (tipoConta) {
            case 1 -> {
                double limite = Double.parseDouble(view.getEntradaLimiteContaCorrente().getText());
                double saldoInicial = Double.parseDouble(view.getEntradaSaldo().getText());
                conta = new ContaCorrente(view.getEntradaCPF().getText(), saldoInicial, limite);          
            }
            case 2 -> 
                conta = new ContaSalario(view.getEntradaCPF().getText(), Double.parseDouble(view.getEntradaSaldo().getText()));
            case 3 -> 
                conta = new ContaPoupanca(view.getEntradaCPF().getText(), Double.parseDouble(view.getEntradaSaldo().getText()));
            default ->
                throw new IllegalStateException("Tipo de conta desconhecido: " + tipoConta);
        }

        Conexao conexao = new Conexao();

        try {
            Connection conn = conexao.getConnection();
            ClienteDAO dao = new ClienteDAO(conn);
            ContaDAO contaDAO = new ContaDAO(conn);
            ResultSet clienteExiste = dao.consultarCliente(cliente);
            if(!clienteExiste.isBeforeFirst()) {
                dao.criarCliente(cliente);
            }
            
            boolean contaCriada = contaDAO.criarConta(conta);

            if (contaCriada) {
                JOptionPane.showMessageDialog(view, "Usuário Criado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Usuário/Conta já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException err) {
            System.out.println(err);
            JOptionPane.showMessageDialog(view, "Erro de conexão!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
