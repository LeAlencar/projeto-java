package model;

/**
 *
 * @author leandroalencar
 */
public class Cliente extends Pessoa {

    private int id;

    public Cliente(String nome, String senha, String cpf) {
        super(nome, senha, cpf);
    }

    public int getId() {
        return id;
    }

    public Cliente() {
    }

}
