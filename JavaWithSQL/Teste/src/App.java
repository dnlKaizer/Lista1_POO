import DAO.UsuarioDAO;
import entity.Usuario;

public class App {
    public static void main(String[] args) throws Exception {

        Usuario user = Usuario.getInstance(1, "Roger Guedes", "rogerin", "rogerin1234");
        new UsuarioDAO().cadastrarUsuario(user);
    }
}
