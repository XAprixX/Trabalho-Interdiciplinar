package CadastroCliente;

import Cliente.Cliente;
import Zelda_CarteiraDigital.Zelda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaComBanco.Conexao;
import javax.swing.JOptionPane;
import org.postgresql.core.ConnectionFactory;

public class CadastroDAO {
    //DAO -> é apenas uma sigla para 
    //Data Access Object 

    //CRIAR UM MÉTODO PARA INSERIR
    public void save(Cliente m) {
        String SQL1 = "INSERT INTO aprix.cliente "
                + "(nome, cpf, valor_conta, conta, codigo_segurança, data_nascimento, senha_conta) "
                + " Values ( ?, ?, ?, ?, ?, ?, ?); ";

        try {

            Conexao c = new Conexao();
            Connection conexao = c.obterConexao();
            PreparedStatement stmt = conexao.prepareStatement(SQL1);

            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getCPF());
            stmt.setInt(3, (int) m.getValor_conta());
            stmt.setString(4, m.getConta());
            //ps1.setString(3, m.getDatanascimento());
            stmt.setInt(5, m.getCodigo_segurança());
            stmt.setString(6, m.getData_nascimento());
            stmt.setInt(7, m.getSenha_conta());

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(CadastroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removeById(Cliente m) {

        String sql = "DELETE FROM aprix.cliente WHERE CPF = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            Conexao c = new Conexao();
            Connection conexao = c.obterConexao();
            pstm = conn.prepareStatement(sql);

            pstm.setString(2, m.getCPF());
            pstm.execute();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    public void update(Cliente m) {

        String sql = "UPDATE aprix.cliente SET Nome = ?, cpf = ?, Valor_Conta = ?,Conta = ?,Codigo_segurança = ?,Data_nascimento = ?,Senha_conta = ?"
                + " WHERE cpf = '" + m.getCPF() + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            Conexao c = new Conexao();
            Connection conexao = c.obterConexao();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, m.getNome());
            pstm.setString(2, m.getCPF());
            pstm.setInt(3, (int) m.getValor_conta());
            pstm.setString(4, m.getConta());
            //ps1.setString(3, m.getDatanascimento());
            pstm.setInt(5, m.getCodigo_segurança());
            pstm.setString(6, m.getData_nascimento());
            pstm.setInt(7, m.getSenha_conta());

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            //Fecha as conexões
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    public List<Cliente> getClientes() {

        String sql = "SELECT * FROM aprix.cliente";

        List<Cliente> cli = new ArrayList<Cliente>();

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco de dados
        ResultSet rset = null;
        List<Cliente> Cliente = null;

        try {
            Conexao c = new Conexao();
            Connection conexao = c.obterConexao();

            pstm = conexao.prepareStatement(sql);
            rset = pstm.executeQuery();

            //Enquanto existir dados no banco de dados, faça
            while (rset.next()) {

                Cliente clie = new Cliente();

                clie.setNome(rset.getString("Nome"));
                clie.setCPF(rset.getString("cpf"));
                clie.setValor_conta(rset.getDouble("valor_conta"));
                clie.setConta(rset.getString("conta"));
                clie.setCodigo_segurança(rset.getInt("codigo_segurança"));
                clie.setData_nascimento(rset.getString("data_nascimento"));
                clie.setSenha_conta(rset.getInt("senha_conta"));

                cli.add(clie);
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {

                if (rset != null) {

                    rset.close();
                }

                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return cli;
    }

    public static List<Cliente> MenuLista() throws Exception {
        PreparedStatement pstm = null;
        Connection conn = null;

        List<Cliente> lista = new ArrayList();
        Cliente obj;
        try {
            Conexao c = new Conexao();

            Connection conexao = c.obterConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from menu order by descricao");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj = new Cliente();
                obj.setCPF(rs.getString("cpf"));
                obj.setValor_conta(rs.getDouble("valor_conta"));
                obj.setCodigo_segurança(rs.getInt("codigo_segurança"));
                lista.add(obj);
            }
        } catch (Exception e) {
            return null;
        } finally {

            try {

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return lista;
    }

    public void updateSaldo(Cliente m) {

        String sql = "UPDATE aprix.cliente SET valor_conta = ? "
                + " WHERE cpf = '" + m.getCPF() + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            Conexao c = new Conexao();
            Connection conexao = c.obterConexao();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conexao.prepareStatement(sql);
            pstm.setDouble(1, m.getValor_conta());

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            //Fecha as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    public void updateSaldoCliente2(Cliente n) {

        String sql = "UPDATE aprix.cliente SET valor_conta =  + ?"
                + " WHERE cpf = '" + n.getCPF() + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            Conexao c = new Conexao();
            Connection conexao = c.obterConexao();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conexao.prepareStatement(sql);
            pstm.setDouble(1, n.getValor_conta());
            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            //Fecha as conexões
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

}
