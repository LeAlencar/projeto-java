package DAO;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Cliente;

/**
 *
 * @author leandroalencar
 */
public class ClienteDAO {

    private Connection conn;

    public ClienteDAO() {
    }

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public ResultSet consultarCliente(Cliente cliente) throws SQLException {
        String sql = "SELECT * FROM contas WHERE cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cliente.getCpf());
        statement.execute();

        ResultSet resultado = statement.getResultSet();
        return resultado;
    }

    public ResultSet criarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, senha, cpf) VALUES (?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getSenha());
        statement.setString(3, cliente.getCpf());
        statement.executeUpdate();

        ResultSet resultado = statement.getResultSet();
        return resultado;

    }

    public void excluirCliente(Cliente cliente) throws SQLException {
        String sql = "DELETE FROM clientes WHERE cpf = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cliente.getCpf());
            statement.executeUpdate();
        }
    }
    
    

}
