package coffeetime.gui.login;

import coffeetime.base.Usuario;
import coffeetime.util.Util;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class Login extends JDialog {
    private JPanel contentPane;
    private JPasswordField txtContrasena;
    private JTextField txtUsuario;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private ArrayList<Usuario> usuarios;
    private ResourceBundle idioma;

    public Login() {
        cargarUsuarios();
        if (usuarios == null) {
            dispose();
        } else {
            idioma = ResourceBundle.getBundle("idioma");
            setContentPane(contentPane);
            setUndecorated(true);
            initComponents();
            pack();
            setLocationRelativeTo(null);
            initHandlers();
            setModal(true);
            setVisible(true);
        }
    }

    private void initComponents() {
        contentPane.getRootPane().setDefaultButton(btnAceptar);
        txtUsuario.requestFocus();
    }

    private void initHandlers() {
        btnCancelar.addActionListener(e -> System.exit(0));
        btnAceptar.addActionListener(e -> comprobarUsuario());
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
            dispose();
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
            if (usuario.getUsuario().equals(txtUsuario.getText())) {
                StringBuilder contrasena = new StringBuilder();
                for (char caracter : txtContrasena.getPassword()) {
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
            dispose();
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
