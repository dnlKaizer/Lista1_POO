import java.util.List;

import DAO.UsuarioDAO;
import entity.Usuario;

public class App {
    public static void main(String[] args) throws Exception {

        Usuario user = Usuario.getInstance(1, "Roger Guedes", "rogerin", "rogerin1234");
        new UsuarioDAO().cadastrarUsuario(user);
        List<Usuario> users = new UsuarioDAO().lerUsuario();
        user = users.get(0);
        System.out.println(user.getCodigo());
        System.out.println(user.getNome());
        System.out.println(user.getLogin());
        System.out.println(user.getSenha());
    }
}
