
package CadastroCliente;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaComBanco.Conexao;
import org.postgresql.core.ConnectionFactory;

/**
 *
 * @author Samuelson
 */
public class UsuarioDAO {

    public boolean checkLogin(String cpf, String senha_conta) {

         Conexao c = new Conexao();
        
     Connection conexao = c.obterConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

   

        try {

            stmt = conexao.prepareStatement("SELECT * FROM aprix.cliente WHERE cpf = ? and senha_conta = ?");
            stmt.setString(1, cpf);
            stmt.setString(2, senha_conta);

            rs = stmt.executeQuery();
            
         
            return (rs.next());
            


        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             c.closeConnection(conexao, stmt, rs);
        }

        return false;

    }

}

