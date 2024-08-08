import java.util.List;

import DAO.UsuarioDAO;
import entity.Usuario;

public class App {
    public static void main(String[] args) throws Exception {
        List<Usuario> usuarios = new UsuarioDAO().read();
        for (Usuario usuario : usuarios) {
            new UsuarioDAO().delete(usuario);
        }
    }
}
