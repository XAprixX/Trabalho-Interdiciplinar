package javaComBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.core.ConnectionFactory;


public class Conexao {
    String link="jdbc:postgresql://10.90.24.56/aula";
    String usuario="aula";
    String senha="aula";
    private Connection con = null;
      private String jdbcDriver = null;
     
    
    public Connection obterConexao(){
         try {
             
   jdbcDriver = "org.postgresql.Driver";
    if (con == null) {
      con = DriverManager.getConnection(link, usuario, senha);
    } else if (con.isClosed()) {
      con = null;
      return obterConexao();
    }
  }catch (SQLException e) {

    //TODO: use um sistema de log apropriado.

    e.printStackTrace();
  }
        //TODO: use um sistema de log apropriado.
  return con;
}
     public static void closeConnection(Connection con) {               
          PreparedStatement stmt = null;         
            ResultSet rs = null;

        try {

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConnection(con);

        try {

            if (rs != null) {
                rs.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    }
    

