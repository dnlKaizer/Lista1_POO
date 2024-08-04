package DAO;
// DATA ACCESS OBJECT

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import entity.Usuario;

public class UsuarioDAO {
    
    public void create(Usuario usuario) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO TbUsuario (NmUsuario, Login, Senha) VALUES (?, ?, ?)");

            // Passando os parâmetros
            statement.setString(1, usuario.getNmUsuario());
            statement.setString(2, usuario.getLogin());
            statement.setString(3, usuario.getSenha());

            // Responsável por INSERT, UPDATE e DELETE
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechando a Connection e o Statement
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void update(Usuario usuario) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE TbUsuario SET NmUsuario = ?, Login = ?, Senha = ? WHERE CdUsuario = ?");

            // Passando os parâmetros
            statement.setString(1, usuario.getNmUsuario());
            statement.setString(2, usuario.getLogin());
            statement.setString(3, usuario.getSenha());
            statement.setInt(4, usuario.getCdUsuario());

            // Responsável por INSERT, UPDATE e DELETE
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechando a Connection e o Statement
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<Usuario> read() {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Usuario> usuarios = new ArrayList<>();

        try {
            statement = connection.prepareStatement("SELECT * FROM TbUsuario");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Usuario usuario = Usuario.getInstance(
                resultSet.getInt("CdUsuario"), 
                resultSet.getString("NmUsuario"), 
                resultSet.getString("Login"), 
                resultSet.getString("Senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return usuarios;
    }

}
