package coffeetime.gui.login;

import coffeetime.base.Usuario;
import coffeetime.util.Util;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class ControladorLogin {

    private final Login login;
    private ArrayList<Usuario> usuarios;
    private ResourceBundle idioma;

    public ControladorLogin() {
        login = new Login();
        cargarUsuarios();
        if (usuarios != null) {
            idioma = Util.obtenerTraducciones();
            crearAtajos();
            initHandlers();
            login.setVisible(true);
        }
    }

    private void initHandlers() {
        login.btnCancelar.addActionListener(e -> System.exit(0));
        login.btnAceptar.addActionListener(e -> comprobarUsuario());
    }

    private void crearAtajos() {
        login.btnAceptar.setMnemonic(KeyEvent.VK_1);
        login.btnCancelar.setMnemonic(KeyEvent.VK_2);
    }

    private void cargarUsuarios() {
        boolean sesionGuardada = false;
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("data/account.conf"));
            String nivelUsuario = (String) properties.get("NivelUsuario");
            if (!nivelUsuario.equals("")) {
                sesionGuardada = true;
            }
        } catch (Exception ignored) {
        }

        if (sesionGuardada) {
            login.dispose();
        } else {
            try {
                FileInputStream flujoEntrada = new FileInputStream("data/usuarios.dat");
                ObjectInputStream deserializador = new ObjectInputStream(flujoEntrada);
                usuarios = (ArrayList<Usuario>) deserializador.readObject();
                deserializador.close();
                if (usuarios.size() <= 0) {
                    usuarios = null;
                }
            } catch (Exception e) {
                usuarios = null;
            }
        }

    }

    private void comprobarUsuario() {
        boolean usuarioEncontrado = false;
        boolean usuarioCorrecto = false;
        Usuario usuarioLogueado = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(login.txtUsuario.getText())) {
                StringBuilder contrasena = new StringBuilder();
                for (char caracter : login.txtContrasena.getPassword()) {
                    contrasena.append(caracter);
                }
                if (usuario.getContrasena().equals(contrasena.toString())) {
                    usuarioCorrecto = true;
                    usuarioLogueado = usuario;
                } else {
                    Util.mostrarError(idioma.getString("error.contrasena"));
                }
                usuarioEncontrado = true;
                break;
            }
        }
        if (!usuarioEncontrado) {
            Util.mostrarError(idioma.getString("error.usuario"));
        } else if (usuarioCorrecto) {
            login.dispose();
            guardarInicioDeSesion(usuarioLogueado);
        }
    }

    private void guardarInicioDeSesion(Usuario usuario) {
        try {
            Properties propiedades = new Properties();
            propiedades.put("Usuario", usuario.getUsuario());
            propiedades.put("Contrasena", usuario.getContrasena());
            propiedades.put("NivelUsuario", String.valueOf(usuario.getTipoUsuario()));
            try {
                propiedades.store(new FileWriter("data/account.conf"), "Coffe Time");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
