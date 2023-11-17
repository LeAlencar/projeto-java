package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import model.ContaSalario;

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

    private Conta criarContaPorTipo(ResultSet resultSet) throws SQLException {
        double saldo = resultSet.getDouble("saldo");
        double limite = 0;
        String cpf = resultSet.getString("cpf");
        String tipo = resultSet.getString("tipo");
        if ("corrente".equals(tipo)) {
            limite = resultSet.getDouble("limite");
        }
         

        switch (tipo) {
            case "corrente" -> {
                return new ContaCorrente(cpf, saldo, limite);
            }
            case "salario" -> {
                return new ContaSalario(cpf, saldo);
            }
            case "poupanca" -> {
                return new ContaPoupanca(cpf, saldo);
            }
            default -> throw new SQLException("Tipo de conta desconhecido: " + tipo);
        }
    }

    public List<Conta> exibirTodasContas() throws SQLException {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM contas";
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Conta conta = criarContaPorTipo(resultSet);
                contas.add(conta);
            }
        }
        return contas;
    }
    
    public List<Conta> exibirSaldoContasPorCPF(String cpf) throws SQLException {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM contas WHERE cpf = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Conta conta = criarContaPorTipo(resultSet);
                    contas.add(conta);
                }
            }
        }
        return contas;
    }

    public boolean criarConta(Conta conta) throws SQLException {
        String sql = "INSERT INTO contas (saldo, tipo, cpf, limite) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
      
            statement.setDouble(1, conta.getSaldo());
            statement.setString(2, conta.getTipo()); 
            statement.setString(3, conta.getCpf());

            if (conta instanceof ContaCorrente contaCorrente) {
                statement.setDouble(4, contaCorrente.getLimite());
            } else {
                statement.setNull(4, java.sql.Types.NULL);
            }

            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0;
        }
    }
    
    public boolean excluirContasPorCPF(String cpf) throws SQLException {
        String sql = "DELETE FROM contas WHERE cpf = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cpf);
            int excluiu = statement.executeUpdate();
            return excluiu > 0;
        }
    }
}
