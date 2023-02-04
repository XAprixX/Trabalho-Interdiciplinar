
package CadastroCliente;

import Cliente.Cliente;

public class Trasferencia extends Cliente{
    
    private Double Trasferencia;

    public Trasferencia(Double Trasferencia, String Nome, String CPF, double Saldo, String Conta, int Segurança, String DDA, int Senha) {
        super(Nome, CPF, Saldo, Conta, Segurança, DDA, Senha);
        this.Trasferencia = Trasferencia;
    }

    public Trasferencia(Double Trasferencia) {
        this.Trasferencia = Trasferencia;
    }

    public Double getTrasferencia() {
        return Trasferencia;
    }

    public void setTrasferencia(Double Trasferencia) {
        this.Trasferencia = Trasferencia;
    }
    
    
   
    
}
