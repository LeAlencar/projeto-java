package model;

/**
 *
 * @author leandroalencar
 */
public abstract class Conta {

    private double saldo;
    private double limite;
    private String tipo;
    private String cpf;
    
    public Conta() {}
    
    public Conta(String tipo, double saldo, String cpf) {

        this.saldo = saldo;
        this.tipo = tipo;
        this.cpf = cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTipo() {
        return tipo;
    }
    
    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public abstract boolean sacar(double valor);

    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        }
    }
}
