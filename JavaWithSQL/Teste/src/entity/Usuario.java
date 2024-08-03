package entity;

public class Usuario {
    private int codigo;
    private String nome;
    private String login;
    private String senha;

    private Usuario(int codigo, String nome, String login, String senha) {
        this.codigo = codigo;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
    public static Usuario getInstance(int codigo, String nome, String login, String senha) {
        if (codigo < 0) {
            return null;
        }
        if (nome.length() > 100) {
            return null;
        }
        if (login.length() > 50) {
            return null;
        }
        if (senha.length() > 50) {
            return null;
        }
        return new Usuario(codigo, nome, login, senha);
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
