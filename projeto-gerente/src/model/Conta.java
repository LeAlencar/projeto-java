package model;

/**
 *
 * @author leandroalencar
 */
public class Conta {
    private String cpf;
    private int tipo;

    @Override
    public String toString() {
        return "Conta{" + "cpf=" + cpf + ", tipo=" + tipo + '}';
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Conta(String cpf, int tipo) {
        this.cpf = cpf;
        this.tipo = tipo;
    }

    public Conta() {
    }
}
