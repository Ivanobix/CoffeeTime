package coffeetime.gui.otros;

import coffeetime.base.Usuario;
import coffeetime.util.Util;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class EliminacionUsuarios extends JDialog {
    private JPanel contentPane;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JComboBox<Usuario> cbUsuario;
    private ArrayList<Usuario> usuarios;
    private ResourceBundle idioma;

    public EliminacionUsuarios() {
        idioma = Util.obtenerTraducciones();
        setContentPane(contentPane);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        initHandlers();
        setVisible(true);
    }

    private void initComponents() {
        contentPane.getRootPane().setDefaultButton(btnAceptar);
        btnAceptar.requestFocus();

        rellenarListaUsuarios();

    }

    private void initHandlers() {
        btnCancelar.addActionListener(e -> dispose());
        btnAceptar.addActionListener(e -> eliminarUsuario());
    }

    private void rellenarListaUsuarios() {
        usuarios = null;
        try {
            FileInputStream flujoEntrada = new FileInputStream("data/usuarios.dat");
            ObjectInputStream deserializador = new ObjectInputStream(flujoEntrada);
            usuarios = (ArrayList<Usuario>) deserializador.readObject();
            deserializador.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (usuarios != null) {
            for (Usuario usuario : usuarios) {
                cbUsuario.addItem(usuario);
            }
        }
    }

    private void eliminarUsuario() {
        if (cbUsuario.getSelectedIndex() != -1) {
            try {
                FileOutputStream flujoSalida = new FileOutputStream("data/usuarios.dat");
                ObjectOutputStream serializador = new ObjectOutputStream(flujoSalida);
                usuarios.remove(cbUsuario.getSelectedIndex());
                serializador.writeObject(usuarios);
                serializador.close();
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Properties propiedades = new Properties();
                propiedades.put("Usuario", "");
                propiedades.put("Contrasena", "");
                propiedades.put("NivelUsuario", "");
                propiedades.store(new FileWriter("data/account.conf"), "Coffe Time");

            } catch (Exception ignored) {
            }
        } else {
            Util.mostrarError(idioma.getString("error.seleccionarusuario"));
        }

    }
}
