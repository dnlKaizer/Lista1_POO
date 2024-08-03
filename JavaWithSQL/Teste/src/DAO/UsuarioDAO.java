package DAO;
// DATA ACCESS OBJECT

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import entity.Usuario;

public class UsuarioDAO {
    
    public void cadastrarUsuario(Usuario user) {
        Connection connection = Conexao.getConexao();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO USUARIO (NmUsuario, Login, Senha) VALUES (?, ?, ?)");

            // Passando os parâmetros
            statement.setString(1, user.getNome());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getSenha());

            // Responsável por INSERT, UPDATE e DELETE
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechando a Connection e o Statement
            Conexao.closeConexao(connection, statement);
        }

    }

}
