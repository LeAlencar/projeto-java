
package DAO;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author leandroalencar
 */
public class Conexao {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/projeto-java";
        
        Connection conexao = DriverManager.getConnection(url, "postgres", "docker");
        return conexao;
    }
    
}
