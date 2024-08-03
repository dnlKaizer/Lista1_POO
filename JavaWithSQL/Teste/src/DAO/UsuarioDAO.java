package DAO;
// DATA ACCESS OBJECT

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entity.Usuario;

public class UsuarioDAO {
    
    public void cadastrarUsuario(Usuario usuario) {
        Connection connection = Conexao.getConexao();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO TbUsuario (NmUsuario, Login, Senha) VALUES (?, ?, ?)");

            // Passando os parâmetros
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getLogin());
            statement.setString(3, usuario.getSenha());

            // Responsável por INSERT, UPDATE e DELETE
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechando a Connection e o Statement
            Conexao.closeConexao(connection, statement);
        }

    }

    public List<Usuario> lerUsuario() {
        Connection connection = Conexao.getConexao();
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
            Conexao.closeConexao(connection, statement, resultSet);
        }

        return usuarios;
    }

}
