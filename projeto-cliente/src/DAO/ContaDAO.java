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
import model.SaldoELimite;
import model.Transacao;
import java.util.Date;

/**
 *
 * @author leandroalencar
 */
public class ContaDAO {

    private Connection conn;

    /**
     *
     */
    public ContaDAO() {
    }

    /**
     *
     * @param conn
     */
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
            default ->
                throw new SQLException("Tipo de conta desconhecido: " + tipo);
        }
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Conta> exibirTodasContas() throws SQLException {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM contas";
        try (PreparedStatement statement = conn.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Conta conta = criarContaPorTipo(resultSet);
                contas.add(conta);
            }
        }
        return contas;
    }

    /**
     *
     * @param cpf
     * @param senha
     * @return
     * @throws SQLException
     */
    public List<Conta> exibirSaldoContasPorCPFSenha(String cpf, String senha) throws SQLException {
        List<Conta> contas = new ArrayList<>();
        String usuarioValidacao = "SELECT * FROM clientes WHERE cpf = ? AND senha = ?";
        PreparedStatement preparedStatementValidacao = conn.prepareStatement(usuarioValidacao);
        preparedStatementValidacao.setString(1, cpf);
        preparedStatementValidacao.setString(2, senha);
        ResultSet resultadoValidacao = preparedStatementValidacao.executeQuery();

        if (resultadoValidacao.next()) {

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
        }
        return contas;
    }

    /**
     *
     * @param cpf
     * @param tipoConta
     * @return
     * @throws SQLException
     */
    public SaldoELimite obterSaldoPorCPFeConta(String cpf, String tipoConta) throws SQLException {
        double saldo = 0.0; // Valor padrão caso nenhum resultado seja encontrado
        double limite = 0.0;
        SaldoELimite saldoELimite = null;
        String sql = "SELECT saldo, limite FROM contas WHERE cpf = ? AND tipo = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);
            preparedStatement.setString(2, tipoConta);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    saldo = resultSet.getDouble("saldo");
                    limite = resultSet.getDouble("limite");
                    saldoELimite = new SaldoELimite(saldo, limite);
                }
            }
        }

        return saldoELimite;
    }

    /**
     *
     * @param cpf
     * @param senha
     * @param tipoConta
     * @param valor
     * @param saldoAtual
     * @throws SQLException
     */
    public void debitarValor(String cpf, String senha, String tipoConta, double valor, double saldoAtual) throws SQLException {
        String usuarioValidacao = "SELECT * FROM clientes WHERE cpf = ? AND senha = ?";
        PreparedStatement preparedStatementValidacao = conn.prepareStatement(usuarioValidacao);
        preparedStatementValidacao.setString(1, cpf);
        preparedStatementValidacao.setString(2, senha);
        ResultSet resultadoValidacao = preparedStatementValidacao.executeQuery();

        if (resultadoValidacao.next()) {

            Date dataTransacao = new Date(); // Substitua pela data da transação

            // Inserir a transação na tabela de transações
            String sqlInserirTransacao = "INSERT INTO transacoes (cpf, tipo_transacao, valor, tipo_conta, data_transacao) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatementTransacao = conn.prepareStatement(sqlInserirTransacao);
            preparedStatementTransacao.setString(1, cpf);
            preparedStatementTransacao.setString(2, "debito");
            preparedStatementTransacao.setDouble(3, valor);
            preparedStatementTransacao.setString(4, tipoConta);
            preparedStatementTransacao.setDate(5, new java.sql.Date(dataTransacao.getTime()));

            preparedStatementTransacao.executeUpdate();

            String sqlAtualizarSaldo = "UPDATE contas SET saldo = ? WHERE cpf = ? AND tipo = ?";

            PreparedStatement preparedStatementSaldo = conn.prepareStatement(sqlAtualizarSaldo);
            preparedStatementSaldo.setDouble(1, saldoAtual);
            preparedStatementSaldo.setString(2, cpf);
            preparedStatementSaldo.setString(3, tipoConta);

            preparedStatementSaldo.executeUpdate();
        }
    }

    /**
     *
     * @param cpf
     * @param senha
     * @param tipoConta
     * @param valor
     * @param saldoAtual
     * @throws SQLException
     */
    public void depositarValor(String cpf, String senha, String tipoConta, double valor, double saldoAtual) throws SQLException {
        String usuarioValidacao = "SELECT * FROM clientes WHERE cpf = ? AND senha = ?";
        PreparedStatement preparedStatementValidacao = conn.prepareStatement(usuarioValidacao);
        preparedStatementValidacao.setString(1, cpf);
        preparedStatementValidacao.setString(2, senha);
        ResultSet resultadoValidacao = preparedStatementValidacao.executeQuery();

        if (resultadoValidacao.next()) {

            Date dataTransacao = new Date(); // Substitua pela data da transação

            // Inserir a transação na tabela de transações
            String sqlInserirTransacao = "INSERT INTO transacoes (cpf, tipo_transacao, valor, tipo_conta, data_transacao) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatementTransacao = conn.prepareStatement(sqlInserirTransacao);
            preparedStatementTransacao.setString(1, cpf);
            preparedStatementTransacao.setString(2, "deposito");
            preparedStatementTransacao.setDouble(3, valor);
            preparedStatementTransacao.setString(4, tipoConta);
            preparedStatementTransacao.setDate(5, new java.sql.Date(dataTransacao.getTime()));

            preparedStatementTransacao.executeUpdate();

            String sqlAtualizarSaldo = "UPDATE contas SET saldo = ? WHERE cpf = ? AND tipo = ?";

            PreparedStatement preparedStatementSaldo = conn.prepareStatement(sqlAtualizarSaldo);
            preparedStatementSaldo.setDouble(1, saldoAtual);
            preparedStatementSaldo.setString(2, cpf);
            preparedStatementSaldo.setString(3, tipoConta);

            preparedStatementSaldo.executeUpdate();
        }
    }

    /**
     *
     * @param cpf
     * @param senha
     * @return
     * @throws SQLException
     */
    public List<Transacao> listarTransacoes(String cpf, String senha) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();

        String usuarioValidacao = "SELECT * FROM clientes WHERE cpf = ? AND senha = ?";
        PreparedStatement preparedStatementValidacao = conn.prepareStatement(usuarioValidacao);
        preparedStatementValidacao.setString(1, cpf);
        preparedStatementValidacao.setString(2, senha);
        ResultSet resultadoValidacao = preparedStatementValidacao.executeQuery();

        if (resultadoValidacao.next()) {
            String sqlTransacoes = "SELECT * FROM transacoes WHERE cpf = ? AND tipo_conta = 'corrente'";
            PreparedStatement preparedStatementTransacoes = conn.prepareStatement(sqlTransacoes);
            preparedStatementTransacoes.setString(1, cpf);
            ResultSet resultadoTransacoes = preparedStatementTransacoes.executeQuery();

            while (resultadoTransacoes.next()) {
                Transacao transacao = new Transacao();
                transacao.setCpf(resultadoTransacoes.getString("cpf"));
                transacao.setTipoTransacao(resultadoTransacoes.getString("tipo_transacao"));
                transacao.setValor(resultadoTransacoes.getDouble("valor"));
                transacao.setTipoConta(resultadoTransacoes.getString("tipo_conta"));
                transacao.setDataTransacao(resultadoTransacoes.getDate("data_transacao"));

                transacoes.add(transacao);
            }
        }

        return transacoes;
    }
}
