package coffeetime.gui.otros;

import coffeetime.base.Usuario;
import coffeetime.util.Util;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class ControladorEliminacionUsuarios {
    private final EliminacionUsuarios eliminacionUsuarios;
    private final ResourceBundle idioma;
    private ArrayList<Usuario> usuarios;

    public ControladorEliminacionUsuarios(EliminacionUsuarios eliminacionUsuarios) {
        this.eliminacionUsuarios = eliminacionUsuarios;
        idioma = Util.obtenerTraducciones();
        rellenarListaUsuarios();
        initHandlers();
    }

    private void initHandlers() {
        eliminacionUsuarios.btnCancelar.addActionListener(e -> eliminacionUsuarios.dispose());
        eliminacionUsuarios.btnAceptar.addActionListener(e -> eliminarUsuario());
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
                eliminacionUsuarios.cbUsuario.addItem(usuario);
            }
        }
    }

    private void eliminarUsuario() {
        if (eliminacionUsuarios.cbUsuario.getSelectedIndex() != -1) {
            int seguroDeEliminar = Util.mostrarConfirmacion(idioma.getString("confirmacion.eliminarUsuario"));
            if (seguroDeEliminar == JOptionPane.YES_OPTION) {
                try {
                    FileOutputStream flujoSalida = new FileOutputStream("data/usuarios.dat");
                    ObjectOutputStream serializador = new ObjectOutputStream(flujoSalida);
                    usuarios.remove(eliminacionUsuarios.cbUsuario.getSelectedIndex());
                    serializador.writeObject(usuarios);
                    serializador.close();
                    eliminacionUsuarios.dispose();
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
            }
        } else {
            Util.mostrarError(idioma.getString("error.seleccionarusuario"));
        }

    }
}
