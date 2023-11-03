
package controller;
import DAO.Conexao;
import view.CriarCliente;
import model.Cliente;
import model.Conta;
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

    public CriarClienteController(CriarCliente view) {
        this.view = view;
    }

    public void CriarCliente() {
        Cliente cliente = new Cliente(view.getEntradaNome().getText(), view.getEntradaSenha().getText(), view.getEntradaCPF().getText());
        Conta conta = new Conta(view.getEntradaCPF().getText(), view.getTipoContaSelecionada());
        
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            ClienteDAO dao = new ClienteDAO(conn);
            ContaDAO contaDAO = new ContaDAO(conn);
            ResultSet res = dao.criarCliente(cliente);
            ResultSet resConta = contaDAO.criarConta(conta);
            
            if(res.next() && resConta.next()) {
                JOptionPane.showMessageDialog(view, "Usuário Criado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Usuário já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch(SQLException err) {
            JOptionPane.showMessageDialog(view, "Erro de conexão!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
