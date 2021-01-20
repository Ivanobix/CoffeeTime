package coffeetime.gui.principal;

import coffeetime.base.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


public class MenuPrincipal extends Component {
    private static final Color TEMA_OSCURO = new Color(30, 30, 30);
    private static final Color TEMA_CLARO = new Color(255, 255, 255);
    private final ResourceBundle idioma;

    private final JFrame frame;
    JPanel pnPrincipal;
    JPanel pnOpciones;
    JPanel pnDatos;
    JButton btnCafes;
    JButton btnFabricantes;
    JButton btnLotes;
    JMenuItem mnitNuevo;
    JMenuItem mnitGuardar;
    JMenuItem mnitCargar;
    JMenuItem mnitDeshacer;
    JMenuItem mnitPreferencias;
    JMenuItem mnitCerrarSesion;
    JMenuItem mnitUsuarios;

    public MenuPrincipal() {
        idioma = ResourceBundle.getBundle("idioma");
        frame = new JFrame();
        frame.setContentPane(pnPrincipal);
        initComponents();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        cargarUsuario();
        frame.setVisible(true);

    }

    private void initComponents() {
        frame.getRootPane().setDefaultButton(btnFabricantes);
        initBarraDeHerramientas();
        establecerEstiloBotones();
        establecerIdiomaBotones();

        try {
            Properties properties = new Properties();
            properties.load(new FileReader("data/preferencias.conf"));
            if (properties.getProperty("Tema").equals("claro")) {
                establecerTema(TEMA_CLARO);
            } else {
                establecerTema(TEMA_OSCURO);
            }
        } catch (Exception e) {
            establecerTema(TEMA_OSCURO);
        }
    }

    private void establecerTema(Color color) {
        pnDatos.setBackground(color);
        btnLotes.setBackground(color);
        btnFabricantes.setBackground(color);
        btnCafes.setBackground(color);

    }

    private void establecerEstiloBotones() {
        btnCafes.setBorderPainted(false);
        btnFabricantes.setBorderPainted(false);
        btnLotes.setBorderPainted(false);
    }

    private void establecerIdiomaBotones() {
        if (Locale.getDefault().getLanguage().equals("en")) {
            btnCafes.setIcon(new ImageIcon("media/menuPrincipal/cafes_en.png"));
            btnLotes.setIcon(new ImageIcon("media/menuPrincipal/lotes_en.png"));
            btnFabricantes.setIcon(new ImageIcon("media/menuPrincipal/fabricantes_en.png"));
        } else if (Locale.getDefault().getLanguage().equals("fr")) {
            btnCafes.setIcon(new ImageIcon("media/menuPrincipal/cafes_fr.png"));
            btnLotes.setIcon(new ImageIcon("media/menuPrincipal/lotes_fr.png"));
            btnFabricantes.setIcon(new ImageIcon("media/menuPrincipal/fabricantes_fr.png"));
        }
    }

    private void initBarraDeHerramientas() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnArchivo = new JMenu(idioma.getString("menu.archivo"));
        mnArchivo.setMnemonic(KeyEvent.VK_A);
        menuBar.add(mnArchivo);

        mnitGuardar = new JMenuItem(idioma.getString("menu.guardar"), new ImageIcon("media/herramientas/guardar.png"));
        mnitGuardar.setActionCommand("mnitGuardar");
        mnitGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        mnArchivo.add(mnitGuardar);

        mnitCargar = new JMenuItem(idioma.getString("menu.cargar"), new ImageIcon("media/herramientas/cargar.png"));
        mnitCargar.setActionCommand("mnitCargar");
        mnitCargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
        mnArchivo.add(mnitCargar);

        mnitNuevo = new JMenuItem(idioma.getString("menu.nuevo"), new ImageIcon("media/herramientas/nuevo.png"));
        mnitNuevo.setActionCommand("mnitNuevo");
        mnitNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK));
        mnArchivo.add(mnitNuevo);

        mnitCerrarSesion = new JMenuItem(idioma.getString("menu.cerrarSesion"), new ImageIcon("media/herramientas/cerrarSesion.png"));
        mnitCerrarSesion.setActionCommand("mnitCerrarSesion");
        mnitCerrarSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.ALT_DOWN_MASK));
        mnArchivo.add(mnitCerrarSesion);

        JMenu mnEditar = new JMenu(idioma.getString("menu.editar"));
        mnEditar.setMnemonic(KeyEvent.VK_E);
        menuBar.add(mnEditar);

        mnitDeshacer = new JMenuItem(idioma.getString("menu.deshacer"), new ImageIcon("media/herramientas/deshacer.png"));
        mnitDeshacer.setActionCommand("mnitDeshacer");
        mnitDeshacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        mnEditar.add(mnitDeshacer);

        mnitPreferencias = new JMenuItem(idioma.getString("menu.preferencias"), new ImageIcon("media/herramientas/preferencias.png"));
        mnitPreferencias.setActionCommand("mnitPreferencias");
        mnitPreferencias.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        mnEditar.add(mnitPreferencias);

        mnitUsuarios = new JMenuItem(idioma.getString("menu.usuarios"), new ImageIcon("media/herramientas/usuarios.png"));
        mnitUsuarios.setActionCommand("mnitUsuarios");
        mnitUsuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK));
        mnEditar.add(mnitUsuarios);

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
            mnitUsuarios.setEnabled(false);
        } else if (nivelUsuario.equals(String.valueOf(Usuario.BASICO))) {
            mnitNuevo.setEnabled(false);
            mnitGuardar.setEnabled(false);
            mnitDeshacer.setEnabled(false);
            mnitUsuarios.setEnabled(false);
        }
    }

}
