package coffeetime.gui.principal;

import coffeetime.util.Util;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


public class MenuPrincipal extends Component {
    private static final Color TEMA_OSCURO = new Color(30, 30, 30);
    private static final Color TEMA_CLARO = new Color(255, 255, 255);
    final JFrame frame;
    private final ResourceBundle idioma;
    JPanel pnPrincipal;
    JPanel pnOpciones;
    JPanel pnDatos;
    JButton btnCafes;
    JButton btnFabricantes;
    JButton btnLotes;
    JMenu mnArchivo;
    JMenu mnEditar;
    JMenuItem mnitNuevo;
    JMenuItem mnitGuardar;
    JMenuItem mnitCargar;
    JMenuItem mnitDeshacer;
    JMenuItem mnitPreferencias;
    JMenuItem mnitCerrarSesion;
    JMenuItem mnitAddUsuarios;
    JMenuItem mnitRemoveUsuarios;

    public MenuPrincipal() {
        idioma = Util.obtenerTraducciones();
        frame = new JFrame();
        frame.setContentPane(pnPrincipal);
        initComponents();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
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
            btnCafes.setIcon(new ImageIcon(this.getClass().getResource("/menuPrincipal/cafes_en.png")));
            btnLotes.setIcon(new ImageIcon(this.getClass().getResource("/menuPrincipal/lotes_en.png")));
            btnFabricantes.setIcon(new ImageIcon(this.getClass().getResource("/menuPrincipal/fabricantes_en.png")));
        } else if (Locale.getDefault().getLanguage().equals("fr")) {
            btnCafes.setIcon(new ImageIcon(this.getClass().getResource("/menuPrincipal/cafes_fr.png")));
            btnLotes.setIcon(new ImageIcon(this.getClass().getResource("/menuPrincipal/lotes_fr.png")));
            btnFabricantes.setIcon(new ImageIcon(this.getClass().getResource("/menuPrincipal/fabricantes_fr.png")));
        }
    }

    private void initBarraDeHerramientas() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        mnArchivo = new JMenu(idioma.getString("menu.archivo"));
        menuBar.add(mnArchivo);

        mnitGuardar = new JMenuItem(idioma.getString("menu.guardar"), new ImageIcon(this.getClass().getResource("/herramientas/guardar.png")));
        mnitGuardar.setActionCommand("mnitGuardar");
        mnArchivo.add(mnitGuardar);

        mnitCargar = new JMenuItem(idioma.getString("menu.cargar"), new ImageIcon(this.getClass().getResource("/herramientas/cargar.png")));
        mnitCargar.setActionCommand("mnitCargar");
        mnArchivo.add(mnitCargar);

        mnitNuevo = new JMenuItem(idioma.getString("menu.nuevo"), new ImageIcon(this.getClass().getResource("/herramientas/nuevo.png")));
        mnitNuevo.setActionCommand("mnitNuevo");
        mnArchivo.add(mnitNuevo);

        mnitCerrarSesion = new JMenuItem(idioma.getString("menu.cerrarSesion"), new ImageIcon(this.getClass().getResource("/herramientas/cerrarSesion.png")));
        mnitCerrarSesion.setActionCommand("mnitCerrarSesion");
        mnArchivo.add(mnitCerrarSesion);

        mnEditar = new JMenu(idioma.getString("menu.editar"));
        menuBar.add(mnEditar);

        mnitDeshacer = new JMenuItem(idioma.getString("menu.deshacer"), new ImageIcon(this.getClass().getResource("/herramientas/deshacer.png")));
        mnitDeshacer.setActionCommand("mnitDeshacer");
        mnEditar.add(mnitDeshacer);

        mnitPreferencias = new JMenuItem(idioma.getString("menu.preferencias"), new ImageIcon(this.getClass().getResource("/herramientas/preferencias.png")));
        mnitPreferencias.setActionCommand("mnitPreferencias");
        mnEditar.add(mnitPreferencias);

        mnitAddUsuarios = new JMenuItem(idioma.getString("menu.usuarios"), new ImageIcon(this.getClass().getResource("/herramientas/anadir_usuario.png")));
        mnitAddUsuarios.setActionCommand("mnitAddUsuarios");
        mnEditar.add(mnitAddUsuarios);

        mnitRemoveUsuarios = new JMenuItem(idioma.getString("menu.usuarios"), new ImageIcon(this.getClass().getResource("/herramientas/eliminar_usuario.png")));
        mnitRemoveUsuarios.setActionCommand("mnitRemoveUsuarios");
        mnEditar.add(mnitRemoveUsuarios);
    }

}
