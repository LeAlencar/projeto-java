package DAO;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Conta;


/**
 *
 * @author leandroalencar
 */
public class ContaDAO {
    private Connection conn;

    public ContaDAO() {
    }

    public ContaDAO(Connection conn) {
        this.conn = conn;
    }
    
    public List consultarTodos() throws SQLException {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT cpf, tipo FROM contas";
        try (Statement statement = conn.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) {

        while (resultSet.next()) {
            String cpf = resultSet.getString("cpf");
            int tipo = resultSet.getInt("tipo");
            Conta conta = new Conta(cpf, tipo);
            contas.add(conta);
        }
    }
        
    return contas;
    }

    public ResultSet criarConta(Conta conta) throws SQLException {
        String sql = "INSERT INTO contas (cpf, tipo) VALUES (?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, conta.getCpf());
            statement.setInt(2, conta.getTipo());
            statement.executeUpdate();
            ResultSet resultado = statement.getResultSet();
            return resultado;
        }
    }
}
