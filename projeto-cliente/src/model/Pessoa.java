package model;

/**
 *
 * @author leandroalencar
 */
public class Pessoa {
    private String nome, senha, cpf;

    /**
     *
     * @param nome
     * @param senha
     * @param cpf
     */
    public Pessoa(String nome, String senha, String cpf) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public String getSenha() {
        return senha;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", senha=" + senha + ", cpf=" + cpf + '}';
    }

    /**
     *
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     *
     * @return
     */
    public String getCpf() {
        return cpf;
    }

    /**
     *
     * @param cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     *
     */
    public Pessoa() {
    }
}
