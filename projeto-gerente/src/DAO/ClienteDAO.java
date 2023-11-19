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

    /**
     *
     */
    public ClienteDAO() {
    }

    /**
     *
     * @param conn
     */
    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     *
     * @param cliente
     * @return
     * @throws SQLException
     */
    public ResultSet consultarCliente(Cliente cliente) throws SQLException {
        String sql = "SELECT * FROM contas WHERE cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cliente.getCpf());
        statement.execute();

        ResultSet resultado = statement.getResultSet();
        return resultado;
    }

    /**
     *
     * @param cliente
     * @return
     * @throws SQLException
     */
    public boolean criarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, senha, cpf) VALUES (?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getSenha());
        statement.setString(3, cliente.getCpf());

        int criou = statement.executeUpdate();

        return criou > 0; 
    }

    /**
     *
     * @param cliente
     * @return
     * @throws SQLException
     */
    public boolean excluirCliente(Cliente cliente) throws SQLException {
        String sql = "DELETE FROM clientes WHERE cpf = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cliente.getCpf());
            int excluiu = statement.executeUpdate();
            return excluiu > 0; 
        }
    }

}
