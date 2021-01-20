package coffeetime.gui.otros;

import coffeetime.base.Usuario;
import coffeetime.util.Util;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreacionUsuarios extends JDialog {
    private JPanel contentPane;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JPasswordField txtContrasena;
    private JTextField txtUsuario;
    private JRadioButton rbAdmin;
    private JRadioButton rbNormal;
    private JRadioButton rbInvitado;
    private ResourceBundle idioma;

    public CreacionUsuarios() {
        idioma = ResourceBundle.getBundle("idioma");
        setContentPane(contentPane);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        initHandlers();
        setVisible(true);

    }

    private void initComponents() {
        contentPane.getRootPane().setDefaultButton(btnAceptar);
        txtUsuario.requestFocus();
    }

    private void initHandlers() {
        btnCancelar.addActionListener(e -> dispose());
        btnAceptar.addActionListener(e -> crearUsuario());
    }

    private void crearUsuario() {
        Usuario usuario = recogerDatos();
        if (usuario != null) {
            ArrayList<Usuario> usuarios;
            try {
                FileInputStream flujoEntrada = new FileInputStream("data/usuarios.dat");
                ObjectInputStream deserializador = new ObjectInputStream(flujoEntrada);
                usuarios = (ArrayList<Usuario>) deserializador.readObject();
                deserializador.close();

            } catch (Exception e) {
                usuarios = new ArrayList<>();
            }

            try {
                FileOutputStream flujoSalida = new FileOutputStream("data/usuarios.dat");
                ObjectOutputStream serializador = new ObjectOutputStream(flujoSalida);
                usuarios.add(usuario);
                serializador.writeObject(usuarios);
                serializador.close();
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private Usuario recogerDatos() {
        Usuario usuario = null;
        String nombreUsuario = txtUsuario.getText().replaceAll(" ", "");
        if ((nombreUsuario.length() > 0)) {
            StringBuilder contrasena = new StringBuilder();
            for (char caracter : txtContrasena.getPassword()) {
                contrasena.append(caracter);
            }
            if (contrasena.toString().replaceAll(" ", "").length() > 0) {
                int nivelUsuario;
                if (rbAdmin.isSelected()) {
                    nivelUsuario = Usuario.ADMIN;
                } else if (rbNormal.isSelected()) {
                    nivelUsuario = Usuario.DEFAULT;
                } else {
                    nivelUsuario = Usuario.BASICO;
                }
                usuario = new Usuario(nombreUsuario, contrasena.toString(), nivelUsuario);
            } else {
                Util.mostrarError(idioma.getString("error.contrasenaVacia"));
            }
        } else {
            Util.mostrarError(idioma.getString("error.usuarioVacio"));
        }
        return usuario;
    }
}
