package coffeetime.gui.principal;

import coffeetime.base.Usuario;
import coffeetime.gui.otros.*;
import coffeetime.gui.usuarios.ControladorCreacionUsuarios;
import coffeetime.gui.usuarios.ControladorEliminacionUsuarios;
import coffeetime.gui.usuarios.CreacionUsuarios;
import coffeetime.gui.usuarios.EliminacionUsuarios;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ControladorMenuPrincipal implements ActionListener, WindowListener {

    public static final String EXTENSION_FICHEROS = ".dat";
    public static final String NOMBRE_EXTENSION_FICHEROS = "dat";
    private final MenuPrincipal menuPrincipal;
    private final Modelo modelo;
    private final ResourceBundle idioma;

    public ControladorMenuPrincipal(MenuPrincipal menuPrincipal, Modelo modelo) {
        this.menuPrincipal = menuPrincipal;
        this.modelo = modelo;
        idioma = Util.obtenerTraducciones();
        initHandlers();
        crearAtajos();
        cargarUsuario();
        comprobarDatosAutoguardado();

    }

    private void initHandlers() {
        menuPrincipal.btnCafes.addActionListener(this);
        menuPrincipal.btnFabricantes.addActionListener(this);
        menuPrincipal.btnLotes.addActionListener(this);

        menuPrincipal.mnArchivo.setMnemonic(KeyEvent.VK_A);
        menuPrincipal.mnEditar.setMnemonic(KeyEvent.VK_E);

        menuPrincipal.mnitGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        menuPrincipal.mnitCargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
        menuPrincipal.mnitNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK));
        menuPrincipal.mnitCerrarSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.ALT_DOWN_MASK));
        menuPrincipal.mnitDeshacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        menuPrincipal.mnitPreferencias.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        menuPrincipal.mnitAddUsuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK));
        menuPrincipal.mnitRemoveUsuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_DOWN_MASK));

        menuPrincipal.mnitGuardar.addActionListener(this);
        menuPrincipal.mnitCargar.addActionListener(this);
        menuPrincipal.mnitNuevo.addActionListener(this);
        menuPrincipal.mnitPreferencias.addActionListener(this);
        menuPrincipal.mnitDeshacer.addActionListener(this);
        menuPrincipal.mnitCerrarSesion.addActionListener(this);
        menuPrincipal.mnitAddUsuarios.addActionListener(this);
        menuPrincipal.mnitRemoveUsuarios.addActionListener(this);

        menuPrincipal.frame.addWindowListener(this);

    }

    private void crearAtajos() {
        menuPrincipal.btnCafes.setMnemonic(KeyEvent.VK_1);
        menuPrincipal.btnFabricantes.setMnemonic(KeyEvent.VK_2);
        menuPrincipal.btnLotes.setMnemonic(KeyEvent.VK_3);
    }

    private void cerrarVentana() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("data/preferencias.conf"));
            String guardadoAutomatico = properties.getProperty("GuardadoAutomatico");
            if (guardadoAutomatico.equals("si")) {
                String rutaGuardado = properties.getProperty("RutaGuardado");
                guardarDatos(rutaGuardado);
            } else {
                mostrarConfirmacionGuardado();
            }

        } catch (Exception e) {
            activarFunciones(String.valueOf(Usuario.ADMIN));
        }

    }

    private void mostrarConfirmacionGuardado() {
        if (modelo.getCambios()) {
            int decision = Util.mostrarConfirmacion(idioma.getString("confirmacion.deseaGuardar"));
            if (decision == JOptionPane.YES_OPTION) {
                guardarDatos(null);
            } else {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }

    private void comprobarDatosAutoguardado() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("data/preferencias.conf"));

            if (properties.getProperty("GuardadoAutomatico").equals("si")) {
                File datosGuardados = new File(properties.getProperty("RutaGuardado") + ".dat");
                if (datosGuardados.exists())
                    modelo.cargarDatos(datosGuardados);
                else
                    Util.mostrarError(idioma.getString("error.datosEliminados"));
            }

        } catch (Exception ignore) {
        }
    }

    private void guardarDatos(String rutaGuardadoAutomatico) {
        if (rutaGuardadoAutomatico == null) {
            JFileChooser selector = new JFileChooser();
            selector.setAcceptAllFileFilterUsed(false);
            selector.setFileFilter(new FileNameExtensionFilter(EXTENSION_FICHEROS, NOMBRE_EXTENSION_FICHEROS));
            int seleccion = selector.showSaveDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File fichero = new File(selector.getSelectedFile().getAbsolutePath() + EXTENSION_FICHEROS);
                try {
                    modelo.guardarDatos(fichero);
                } catch (IOException e1) {
                    Util.mostrarError(idioma.getString("error.guardarFichero"));
                }
            }
        } else {
            try {
                File fichero = new File(rutaGuardadoAutomatico + EXTENSION_FICHEROS);
                modelo.guardarDatos(fichero);
            } catch (Exception ignore) {
            }

        }

    }

    private void cargarDatos() {
        JFileChooser selectorCarga = new JFileChooser();
        selectorCarga.setAcceptAllFileFilterUsed(false);
        selectorCarga.setFileFilter(new FileNameExtensionFilter(EXTENSION_FICHEROS, NOMBRE_EXTENSION_FICHEROS));
        int seleccionCarga = selectorCarga.showOpenDialog(null);
        if (seleccionCarga == JFileChooser.APPROVE_OPTION) {
            File ficheroCarga = selectorCarga.getSelectedFile();
            try {
                modelo.cargarDatos(ficheroCarga);
            } catch (ClassNotFoundException | IOException e1) {
                Util.mostrarError(idioma.getString("error.cargarFichero"));
            }
        }
    }

    private void reiniciarElementos() {
        if (Util.mostrarConfirmacion(idioma.getString("error.seguroDeBorrar")) == Util.ACEPTAR) {
            modelo.reiniciarDatos();
        }
    }

    private void cerrarSesion() {
        try {
            Properties propiedades = new Properties();
            propiedades.put("Usuario", "");
            propiedades.put("Contrasena", "");
            propiedades.put("NivelUsuario", "");
            propiedades.store(new FileWriter("data/account.conf"), "Coffe Time");

        } catch (Exception ignored) {
        }
        System.exit(0);
    }

    private void cargarUsuario() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("data/account.conf"));
            String nivelUsuario = properties.getProperty("NivelUsuario");
            activarFunciones(nivelUsuario);
        } catch (Exception e) {
            activarFunciones(String.valueOf(Usuario.ADMIN));
        }
    }

    private void activarFunciones(String nivelUsuario) {
        if (nivelUsuario.equals(String.valueOf(Usuario.DEFAULT))) {
            menuPrincipal.mnitAddUsuarios.setEnabled(false);
            menuPrincipal.mnitRemoveUsuarios.setEnabled(false);
        } else if (nivelUsuario.equals(String.valueOf(Usuario.BASICO))) {
            menuPrincipal.mnitNuevo.setEnabled(false);
            menuPrincipal.mnitGuardar.setEnabled(false);
            menuPrincipal.mnitDeshacer.setEnabled(false);
            menuPrincipal.mnitAddUsuarios.setEnabled(false);
            menuPrincipal.mnitRemoveUsuarios.setEnabled(false);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnCafes":
                new ControladorSubmenu(new Submenu(Submenu.TYPE_CAFES), modelo);
                break;
            case "btnFabricantes":
                new ControladorSubmenu(new Submenu(Submenu.TYPE_FABRICANTES), modelo);
                break;
            case "btnLotes":
                new ControladorSubmenu(new Submenu(Submenu.TYPE_LOTES), modelo);
                break;
            case "mnitGuardar":
                guardarDatos(null);
                break;
            case "mnitCargar":
                cargarDatos();
                break;
            case "mnitNuevo":
                reiniciarElementos();
                break;
            case "mnitPreferencias":
                new ControladorPreferencias(new Preferencias());
                break;
            case "mnitAddUsuarios":
                new ControladorCreacionUsuarios(new CreacionUsuarios());
                break;
            case "mnitRemoveUsuarios":
                new ControladorEliminacionUsuarios(new EliminacionUsuarios());
                break;
            case "mnitDeshacer":
                System.out.println("Deshacer");
                break;
            case "mnitCerrarSesion":
                cerrarSesion();
                break;
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        cerrarVentana();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
