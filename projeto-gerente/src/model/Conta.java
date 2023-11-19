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
    
    /**
     *
     */
    public Conta() {}
    
    /**
     *
     * @param tipo
     * @param saldo
     * @param cpf
     */
    public Conta(String tipo, double saldo, String cpf) {

        this.saldo = saldo;
        this.tipo = tipo;
        this.cpf = cpf;
    }

    /**
     *
     * @return
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     *
     * @return
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     *
     * @return
     */
    public double getLimite() {
        return limite;
    }

    /**
     *
     * @param limite
     */
    public void setLimite(double limite) {
        this.limite = limite;
    }

    /**
     *
     * @param saldo
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
     * @param valor
     * @return
     */
    public abstract boolean sacar(double valor);

    /**
     *
     * @param valor
     */
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
        }
    }
}
