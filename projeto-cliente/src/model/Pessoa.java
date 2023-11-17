package model;

/**
 *
 * @author leandroalencar
 */
public class Pessoa {
    private String nome, senha, cpf;

    public Pessoa(String nome, String senha, String cpf) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", senha=" + senha + ", cpf=" + cpf + '}';
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Pessoa() {
    }
}
