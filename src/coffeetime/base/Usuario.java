package coffeetime.base;

import java.io.Serializable;

public class Usuario implements Serializable {
    public static final int ADMIN = 0;
    public static final int DEFAULT = 1;
    public static final int BASICO = 2;

    private String usuario;
    private String contrasena;
    private int tipoUsuario;

    public Usuario(String usuario, String contrasena, int tipoUsuario) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
