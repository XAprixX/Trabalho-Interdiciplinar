package Cliente;

import CadastroCliente.CadastroDAO;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JOptionPane;



public class Cliente {
    
        private String Nome;
        private String CPF;
        private double valor_conta;
        private String conta;
        private int codigo_segurança;
        private Vector[] codigoSeg = new Vector[3];
        private String data_nascimento;
        private int senha_conta;
    
     
    public Cliente(String Nome, String CPF, double Saldo, String Conta, int Segurança, String DDA, int Senha) {
        this.Nome = Nome;
        this.CPF = CPF;
        this.valor_conta = Saldo;
        this.conta = Conta;
        this.codigo_segurança = Segurança;
        this.data_nascimento = DDA;
        this.senha_conta = Senha;
    }

    public Cliente(String Nome, String CPF, int Segurança, String DDA, int Senha) {
        this.Nome = Nome;
        this.CPF = CPF;
        this.codigo_segurança = Segurança;
        this.data_nascimento = DDA;
        this.senha_conta = Senha;
        this.valor_conta = 0.0;
    }

    private void finalize(String CPF) {
    
    }

    public int getCodigo_segurança() {
        return codigo_segurança;
    }

    public void getCPF(String showInputDialog) {
         this.CPF = CPF;
       
             if (ValidaCPF.CPF(CPF) == true){
                 System.out.println("CPF Valido");
               System.out.printf("%s\n", ValidaCPF.imprimeCPF(CPF)); 
            }else
            System.out.printf("CPF invalido !!!\n");
             
        finalize(CPF);
        
    }

       
   public class ValidaCPF {

    public static boolean CPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
            CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11))
            
            return(false);
   
          
        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int), Foi usado pra qualquer prevenção de erros de conversão
        try {
        // Calculo de verificação
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0
        // Usando a tabela ASCII 48 eh a posicao de '0'
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
        
        }
    
    
        //Colocaçao de "." e "-" no CPF automaticamente
        public static String imprimeCPF(String CPF) {
            return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
            CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
        }
        
        
    
       
        //Classe Para o Usuario informar o CPF
        //Caso o CPF Seja falso o usuario sera informado
        //Não e necessario colocar "." ou "-"
        public class Exemplo {

          public static void CPF2 () {
               
               
           
            Scanner ler = new Scanner(System.in);
            
            String CPF;

            System.out.printf("Informe um CPF: ");
            CPF = ler.next();
            
            System.out.printf("\nResultado:");
                // usando os metodos isCPF() e imprimeCPF() da classe "ValidaCPF"    
             
            if (ValidaCPF.CPF(CPF) == true){
                 System.out.println("CPF Valido");
               System.out.printf("%s\n", ValidaCPF.imprimeCPF(CPF)); 
            }else
            System.out.printf("CPF invalido !!!\n");
               
               
               }
          }
        }

    public Cliente() {
   
    }

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
       
             if (ValidaCPF.CPF(CPF) == true){
                 System.out.println("CPF Valido");
               System.out.printf("%s\n", ValidaCPF.imprimeCPF(CPF)); 
            }else
            System.out.printf("CPF invalido !!!\n");
             
        finalize(CPF);
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
    
    /*Cliente existente = new Cliente("Camarada", "167.920.466-12", 0.0, "Poupança", 345, "12/01/2003", 1234);

    public Cliente getExistente() {
        return existente;
    }

    public void setExistente(Cliente existente) {
        this.existente = existente;
    }*/
    
    public String toString(){
        String resultado = "";
        
        resultado += "          Extrato:     " + "\nNome: " + this.Nome +
                "\nTipo de conta: " + this.conta + 
                "\nSaldo: " + this.valor_conta;
                
        return resultado;
    }
    
  

}