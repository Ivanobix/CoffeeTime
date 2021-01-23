package coffeetime.gui.usuarios;

import coffeetime.base.Usuario;
import coffeetime.util.Util;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorCreacionUsuarios {

    private final CreacionUsuarios creacionUsuarios;
    private final ResourceBundle idioma;

    public ControladorCreacionUsuarios(CreacionUsuarios creacionUsuarios) {
        this.creacionUsuarios = creacionUsuarios;
        idioma = Util.obtenerTraducciones();
        crearAtajos();
        initHandlers();
    }

    private void initHandlers() {
        creacionUsuarios.btnCancelar.addActionListener(e -> creacionUsuarios.dispose());
        creacionUsuarios.btnAceptar.addActionListener(e -> crearUsuario());
    }

    private void crearAtajos() {
        creacionUsuarios.btnAceptar.setMnemonic(KeyEvent.VK_1);
        creacionUsuarios.btnCancelar.setMnemonic(KeyEvent.VK_2);
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
                creacionUsuarios.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private Usuario recogerDatos() {
        Usuario usuario = null;
        String nombreUsuario = creacionUsuarios.txtUsuario.getText().replaceAll(" ", "");
        if ((nombreUsuario.length() > 0)) {
            StringBuilder contrasena = new StringBuilder();
            for (char caracter : creacionUsuarios.txtContrasena.getPassword()) {
                contrasena.append(caracter);
            }
            if (contrasena.toString().replaceAll(" ", "").length() > 0) {
                int nivelUsuario;
                if (creacionUsuarios.rbAdmin.isSelected()) {
                    nivelUsuario = Usuario.ADMIN;
                } else if (creacionUsuarios.rbNormal.isSelected()) {
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
