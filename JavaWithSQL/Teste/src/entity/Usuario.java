package entity;

public class Usuario {
    private int cdUsuario;
    private String nmUsuario;
    private String login;
    private String senha;

    private Usuario(int cdUsuario, String nmUsuario, String login, String senha) {
        this.cdUsuario = cdUsuario;
        this.nmUsuario = nmUsuario;
        this.login = login;
        this.senha = senha;
    }
    public static Usuario getInstance(int cdUsuario, String nmUsuario, String login, String senha) {
        if (cdUsuario < 1) {
            return null;
        }
        if (nmUsuario.length() > 100) {
            return null;
        }
        if (login.length() > 50) {
            return null;
        }
        if (senha.length() > 50) {
            return null;
        }
        return new Usuario(cdUsuario, nmUsuario, login, senha);
    }

    private Usuario(String nmUsuario, String login, String senha) {
        this.nmUsuario = nmUsuario;
        this.login = login;
        this.senha = senha;
    }
    public static Usuario getInstance(String nmUsuario, String login, String senha) {
        if (nmUsuario.length() > 100) {
            return null;
        }
        if (login.length() > 50) {
            return null;
        }
        if (senha.length() > 50) {
            return null;
        }
        return new Usuario(nmUsuario, login, senha);
    }
    
    private Usuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }
    public static Usuario getInstance(int cdUsuario) {
        if (cdUsuario < 1) {
            return null;
        }
        return new Usuario(cdUsuario);
    }

    public int getCdUsuario() {
        return cdUsuario;
    }
    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }
    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
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
