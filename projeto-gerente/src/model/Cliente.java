package model;

/**
 *
 * @author leandroalencar
 */
public class Cliente extends Pessoa {

    private int id;

    /**
     *
     * @param nome
     * @param senha
     * @param cpf
     */
    public Cliente(String nome, String senha, String cpf) {
        super(nome, senha, cpf);
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     */
    public Cliente() {
    }

}
