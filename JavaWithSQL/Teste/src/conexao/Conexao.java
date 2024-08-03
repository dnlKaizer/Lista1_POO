package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/bd_teste";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection connection;

    public static Connection getConexao() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(url, user, password);
            } 
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
