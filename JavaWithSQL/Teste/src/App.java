import java.util.List;

import DAO.UsuarioDAO;
import entity.Usuario;

public class App {
    public static void main(String[] args) throws Exception {

        Usuario usuario = Usuario.getInstance(1, "Roger Guedes", "rogerin", "rogerin1234");
        new UsuarioDAO().create(usuario);
        List<Usuario> usuarios = new UsuarioDAO().read();
        usuario = usuarios.get(0);
        System.out.println(usuario.getCdUsuario());
        System.out.println(usuario.getNmUsuario());
        System.out.println(usuario.getLogin());
        System.out.println(usuario.getSenha());
    }
}
