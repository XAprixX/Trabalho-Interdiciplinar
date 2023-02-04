/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

/**
 *
 * @author 0063412
 */
public class ClienteModel {
        private String Nome;
        private String CPF;
        private double valor_conta;
        private String conta;
        private int codigo_segurança;
        private String data_nascimento;
        private int senha_conta;    

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public double getValor_conta() {
        return valor_conta;
    }

    public void setValor_conta(double valor_conta) {
        this.valor_conta = valor_conta;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public int getCodigo_segurança() {
        return codigo_segurança;
    }

    public void setCodigo_segurança(int codigo_segurança) {
        this.codigo_segurança = codigo_segurança;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getSenha_conta() {
        return senha_conta;
    }

    public void setSenha_conta(int senha_conta) {
        this.senha_conta = senha_conta;
    }

}
