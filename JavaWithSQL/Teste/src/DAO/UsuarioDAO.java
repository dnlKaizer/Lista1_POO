package DAO;
// DATA ACCESS OBJECT

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import entity.Usuario;

public class UsuarioDAO {
    
    public boolean cadastrarUsuario(Usuario user) {
        String sql = "INSERT INTO USUARIO (NOME, LOGIN, SENHA) VALUES (?, ?, ?)";
        boolean verify = false;
    
        PreparedStatement ps = null;
        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getSenha());

            verify = ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return verify;

    }

}
