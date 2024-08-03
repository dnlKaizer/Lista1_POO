import java.util.List;

import DAO.UsuarioDAO;
import entity.Usuario;

public class App {
    public static void main(String[] args) throws Exception {

        new UsuarioDAO().create(Usuario.getInstance("Rodinei", "rodo", "rodo1234"));

        List<Usuario> usuarios = new UsuarioDAO().read();
        Usuario usuario = usuarios.get(1);
        System.out.println(usuario.getCdUsuario());
        System.out.println(usuario.getNmUsuario());
        System.out.println(usuario.getLogin());
        System.out.println(usuario.getSenha());
    }
}
