package Zelda_CarteiraDigital;

import CadastroCliente.CadastroDAO;
import Cliente.Cliente;
import static Cliente.Cliente.ValidaCPF.CPF;
import Cliente.ClienteModel;

import java.util.Scanner;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaComBanco.Conexao;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;

public class Zelda {

    static Connection conexao;
    static ResultSet rs;
    static PreparedStatement pstm;
    
    Zelda Zelda = new Zelda();

    private String NomeBanco;

    public String getNome() {
        return NomeBanco;
    }

    public void setNome(String Nome) {
        this.NomeBanco = Nome;
        Nome = "Zelda";
    }

    public class CPF {

        public static String verifCPF(String CPF) {
            if (CPF.equals("00000000000")
                    || CPF.equals("11111111111")
                    || CPF.equals("22222222222") || CPF.equals("33333333333")
                    || CPF.equals("44444444444") || CPF.equals("55555555555")
                    || CPF.equals("66666666666") || CPF.equals("77777777777")
                    || CPF.equals("88888888888") || CPF.equals("99999999999")
                    || (CPF.length() != 11)) {
                return null;
            }

            char dig10, dig11;
            int sm, i, r, num, peso;

            // "try" - protege o codigo para eventuais erros de conversao de tipo (int), Foi usado pra qualquer prevenção de erros de conversão
            try {
                // Calculo de verificação
                sm = 0;
                peso = 10;
                for (i = 0; i < 9; i++) {
                    // converte o i-esimo caractere do CPF em um numero:
                    // por exemplo, transforma o caractere '0' no inteiro 0
                    // Usando a tabela ASCII 48 eh a posicao de '0'
                    num = (int) (CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11)) {
                    dig10 = '0';
                } else {
                    dig10 = (char) (r + 48); // converte no respectivo caractere numerico
                }
                // Calculo do 2o. Digito Verificador
                sm = 0;
                peso = 11;
                for (i = 0; i < 10; i++) {
                    num = (int) (CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11)) {
                    dig11 = '0';
                } else {
                    dig11 = (char) (r + 48);
                }

                // Verifica se os digitos calculados conferem com os digitos informados.
                if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                } else {
                }
            } catch (InputMismatchException erro) {
            }
            return null;

        }

        //Colocaçao de "." e "-" no CPF automaticamente
        public static String imprimeCPF(String CPF) {
            return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
                    + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
        }

        //Classe Para o Usuario informar o CPF
        //Caso o CPF Seja falso o usuario sera informado
        //Não e necessario colocar "." ou "-"
        public class Exemplo {

            public static void CPF2() {

                Scanner ler = new Scanner(System.in);

                String CPF;

                System.out.printf("Informe um CPF: ");
                CPF = ler.next();

                System.out.printf("\nResultado:");
                // usando os metodos isCPF() e imprimeCPF() da classe "ValidaCPF"    

                if (Cliente.ValidaCPF.CPF(CPF) == true) {
                    System.out.println("CPF Valido");
                    System.out.printf("%s\n", Cliente.ValidaCPF.imprimeCPF(CPF));
                } else {
                    System.out.printf("CPF invalido !!!\n");
                }

            }
        }
    }
    
    public int codigoSeg(){
        Random sorteia = new Random();
        int valor = sorteia.nextInt(0,4);
        int sorte;
        if(valor>1){
            sorte = sorteia.nextInt(48,57);
        }else{
            sorte = sorteia.nextInt(97,122);
        }
        return sorte;
    }
    
    public static void verifSaldo(double val, Cliente cliente, CadastroDAO cadastroDAO){
        
    
          if (val <= cliente.getValor_conta()) {
            cliente.setValor_conta(cliente.getValor_conta() - val);
            cadastroDAO.updateSaldo(cliente);
           
        } else {
            JOptionPane.showInternalMessageDialog(null, "Valor inválido");
                System.exit(0);
        }
    }

    public static int CadastrarCliente() {
         
        /* Ordem:
        private String Nome;
        private String CPF;
        private double valor_conta;
        private String conta;
        private int codigo_segurança;
        private String data_nascimento;
        private int senha_conta;
        */
        
        CadastroDAO cadastroDAO = new CadastroDAO();
        Cliente cliente = new Cliente();
        cliente.setNome(JOptionPane.showInputDialog(" Informa seu nome: " ));
        cliente.setCPF(JOptionPane.showInputDialog("\n Infome seu CPF: "));
        cliente.setValor_conta(0);
        cliente.setConta("poupança");
        cliente.setCodigo_segurança(123);
        cliente.setData_nascimento(JOptionPane.showInputDialog("\n Informe sua Data de Nascimento: "));
        String senha1 = JOptionPane.showInputDialog("\nInfome sua senha de 4 digitos: ");
        
        try{
            cliente.setSenha_conta(Integer.parseInt(senha1));
            
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "A senha deve ser um valor numérico");
        }
        cadastroDAO.save(cliente);

        //Atualiza o contato com cpf = ? com os dados do objeto cliente1
        Cliente cliente1 = new Cliente();
        cliente1.setNome(" ' " + cliente.getNome()+ " ' ");
        cliente1.setCPF(" ' " + cliente.getCPF() + " ' ");
        cliente1.setValor_conta(0);
        cliente1.setConta("poupança");      
        cliente1.setCodigo_segurança(123);
        cliente1.setData_nascimento(" ' " + cliente.getData_nascimento()+ " ' ");
        cliente1.setSenha_conta(cliente.getSenha_conta());

        cadastroDAO.update(cliente1);

 
        //Lista todos os contatos do banco de dados
       for (Cliente c : cadastroDAO.getClientes()) {

           JOptionPane.showMessageDialog(null,"CPF: " + c.getCPF());
        }

       
        return 0;
    }

    public static void Saque() {

      CadastroDAO cadastroDAO = new CadastroDAO();
        Cliente cliente = new Cliente();
        Scanner entrada = new Scanner(System.in);
        

        //Busca do CPF No BD
        JOptionPane.showMessageDialog(null, "REALIZANDO SAQUE... \n");
        cliente.setCPF(JOptionPane.showInputDialog("\n Informe seu CPF: "));
        

        try {

            Conexao c = new Conexao();
            conexao = c.obterConexao();

            
            String SQL = ("SELECT * FROM aprix.cliente"
                    + " where cpf = '" + cliente.getCPF() + "'");
            
  
            pstm = conexao.prepareStatement(SQL);
            rs = pstm.executeQuery();
            
            //passar a query entre parenteses. stmt.executeQuery(SQL) por exemplo : String SQL = "select * from tabela where cpf = '"+ cpfInformado +"'
   
            if(rs == null){
                System.out.println("ResultSet null");
            }
            
            while(rs.next()){
            // JOptionPane.showMessageDialog(null,rs.getString("nome"));
                cliente.setCPF(rs.getString("cpf"));
                //String.valueOf(0)
                cliente.setValor_conta(rs.getDouble("valor_conta"));
                 
            }
        } catch (SQLException ex) {
            
            System.out.print("Erro: " + ex.getMessage() );
            JOptionPane.showMessageDialog(null,"Erro: " + ex.getMessage() );
        }
        
        Double valSacado = Double.parseDouble(JOptionPane.showInputDialog(null, "\n Informe o valor desejado: "));
        
     
        
        //Saque
        verifSaldo(valSacado, cliente, cadastroDAO);

    }
    
    public static void Extrato(){
        
        //165.356.777-44
        
         CadastroDAO cadastroDAO = new CadastroDAO();
        Cliente cliente = new Cliente();
        Scanner entrada = new Scanner(System.in);
        

        //Busca do CPF No BD
       
        cliente.setCPF(JOptionPane.showInputDialog("\n Informe seu CPF: "));
        

        try {

            Conexao c = new Conexao();
            conexao = c.obterConexao();

            
            String SQL = ("SELECT * FROM aprix.cliente"
                    + " where cpf = '" + cliente.getCPF() + "'");
            
  
            pstm = conexao.prepareStatement(SQL);
            rs = pstm.executeQuery();
            
            //passar a query entre parenteses. stmt.executeQuery(SQL) por exemplo : String SQL = "select * from tabela where cpf = '"+ cpfInformado +"'
   
            if(rs == null){
                System.out.println("ResultSet null");
            }
            
            while(rs.next()){
            cliente.setCPF(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            
            cliente.setValor_conta(rs.getDouble("valor_conta" ));
            cliente.setConta(rs.getString("conta"));
            
            //String 9.000.00R$
            String n = String.format("%.2f", cliente.getValor_conta());
            
            JOptionPane.showMessageDialog(null,"Saldo:  " + "R$" + n + 
                    "\n" + "Cliente: " + cliente.getNome() + "\n" + "Conta: " + cliente.getConta());
           
                
                 
            }
        } catch (SQLException ex) {
            
            System.out.print("Erro: " + ex.getMessage() );
            JOptionPane.showMessageDialog(null,"Erro: " + ex.getMessage() );
        }
        
     
    }
    
    
        public static void Trasferencia(){
            
        CadastroDAO cadastroDAO = new CadastroDAO();       
        Cliente cliente = new Cliente();
        Cliente cliente2 = new Cliente();
        

        //Busca do CPF No BD
        JOptionPane.showMessageDialog(null, "Trasferencia. . . \n");
        cliente.setCPF(JOptionPane.showInputDialog("\n Informe o seu CPF: "));
        
//Cliente
        try {

            Conexao c = new Conexao();
            conexao = c.obterConexao();

            
            String SQL = ("SELECT * FROM aprix.cliente"
                    + " where cpf = '" + cliente.getCPF() + "'");
            
  
            pstm = conexao.prepareStatement(SQL);
            rs = pstm.executeQuery();
            
            //passar a query entre parenteses. stmt.executeQuery(SQL) por exemplo : String SQL = "select * from tabela where cpf = '"+ cpfInformado +"'
   
            if(rs == null){
                System.out.println("ResultSet null");
            }
            
            while(rs.next()){
            // JOptionPane.showMessageDialog(null,rs.getString("nome"));
                cliente.setCPF(rs.getString("cpf"));
                 cliente.setValor_conta(rs.getDouble("valor_conta"));
            }
        } catch (SQLException ex) {
            
            System.out.print("Erro: " + ex.getMessage() );
            JOptionPane.showMessageDialog(null,"Erro: " + ex.getMessage() );
        }
        
        
        
       //Cliente2, 167.920.466-12
        
        cliente2.setCPF(JOptionPane.showInputDialog("\n Informe CPF pra quem deseja enviar: "));
        

        try {

            Conexao c = new Conexao();
            conexao = c.obterConexao();

            
            String SQL = ("SELECT * FROM aprix.cliente"
                    + " where cpf = '" + cliente2.getCPF() + "'");
            
  
            pstm = conexao.prepareStatement(SQL);
            rs = pstm.executeQuery();
            
            //passar a query entre parenteses. stmt.executeQuery(SQL) por exemplo : String SQL = "select * from tabela where cpf = '"+ cpfInformado +"'
   
            if(rs == null){
                System.out.println("ResultSet null");
            }
            
            while(rs.next()){
            // JOptionPane.showMessageDialog(null,rs.getString("nome"));
                cliente2.setCPF(rs.getString("cpf"));
                 cliente2.setValor_conta(rs.getDouble("valor_conta"));
            }
        } catch (SQLException ex) {
            
            System.out.print("Erro: " + ex.getMessage() );
            JOptionPane.showMessageDialog(null,"Erro: " + ex.getMessage() );
        }
        
        
        Double valTrasferido = Double.parseDouble(JOptionPane.showInputDialog(null, "\n Informe o valor desejado: "));
        
            verifSaldo(valTrasferido, cliente, cadastroDAO);           
            cliente2.setValor_conta(valTrasferido);
            cadastroDAO.updateSaldo(cliente2);
            
            
        }
       

                    


    public static void cleitin(String[] args) {

        
            JOptionPane.showMessageDialog(null,"    ===Bem-vindo a carteira Digital Zelda===   "
                + "\n  𝐂𝐚𝐫𝐭𝐞𝐢𝐫𝐚 𝐙𝐞𝐥𝐝𝐚 𝐚 𝐂𝐚𝐫𝐭𝐞𝐢𝐫𝐚 𝐝𝐢𝐠𝐢𝐭𝐚𝐥 𝐝𝐚 𝐠𝐞𝐫𝐚ç𝐚𝐨 𝐙 ");
            
        CadastroCliente.CadastroDAO BD = new CadastroDAO();

        int opcao = 0;
        int op = 0;

        String opa
                = ("--Bem-vindo a carteira Digital Zelda--"
                + "\n  Carteira Zelda a Carteira digital da geração Z ");

        String opcoes
                = ("       Zelda: Informartica 2     "
                + "\n\n          ***Opções***     "
                + "\n      1. Cadastro cliente "
                + "\n      2. Saque "
                + "\n      3. Consultar Extrato "
                + "\n      4.Trasferencia "
                + "\n      5. Sair " );

      
        
        opcao = Integer.parseInt(JOptionPane.showInputDialog(null, opcoes, JOptionPane.QUESTION_MESSAGE));
  
                
        

        while (opcao != 6) {
            
            switch (opcao) {
                
                case 1:

                   CadastrarCliente();               
                    return;
                    

                case 2:

                   Saque();                 
                    return;
                    

                case 3:

                   Extrato();
                    return;
                    
                 
                case 4:
                    
                    Trasferencia();
                    return;
                    

                case 5: {

                    String h= (
                   "         Obrigado por ter vindo a nossa Carteira Digital Zelda        "
                        + "\n                                       ---𝒱𝑜𝓁𝓉𝑒 𝒮𝑒𝓂𝓅𝓇𝑒---       ");
                            
                  
                    JOptionPane.showMessageDialog(null, h);
                    JOptionPane.showMessageDialog(null, "       𝙴𝚗𝚌𝚎𝚛𝚛𝚊𝚗𝚍𝚘 𝙿𝚛𝚘𝚐𝚊𝚖𝚊...       ");
                        
                    

                        System.exit(opcao);

                    break;
                }
                
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");

                    
                    return ;

            }
        }
    }
}
