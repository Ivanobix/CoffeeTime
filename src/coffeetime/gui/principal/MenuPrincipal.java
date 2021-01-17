package coffeetime.gui.principal;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;


public class MenuPrincipal extends Component {
    private static final Color TEMA_OSCURO = new Color(21, 21, 21);
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

    public MenuPrincipal() {
        idioma = ResourceBundle.getBundle("idioma");
        frame = new JFrame();
        initComponents();
        frame.setContentPane(pnPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void initComponents() {
        initBarraDeHerramientas();

        pnDatos.setBackground(TEMA_OSCURO);

        btnCafes.setBorderPainted(false);
        btnCafes.setBackground(TEMA_OSCURO);

        btnFabricantes.setBorderPainted(false);
        btnFabricantes.setBackground(TEMA_OSCURO);

        btnLotes.setBorderPainted(false);
        btnLotes.setBackground(TEMA_OSCURO);

    }

    private void initBarraDeHerramientas() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnArchivo = new JMenu(idioma.getString("archivo"));
        menuBar.add(mnArchivo);

        mnitGuardar = new JMenuItem(idioma.getString("guardar"), new ImageIcon("media/herramientas/guardar.png"));
        mnitGuardar.setActionCommand("mnitGuardar");
        mnArchivo.add(mnitGuardar);

        mnitCargar = new JMenuItem(idioma.getString("cargar"), new ImageIcon("media/herramientas/cargar.png"));
        mnitCargar.setActionCommand("mnitCargar");
        mnArchivo.add(mnitCargar);

        mnitNuevo = new JMenuItem(idioma.getString("nuevo"), new ImageIcon("media/herramientas/nuevo.png"));
        mnitNuevo.setActionCommand("mnitNuevo");
        mnArchivo.add(mnitNuevo);

        mnitCerrarSesion = new JMenuItem(idioma.getString("cerrarSesion"), new ImageIcon("media/herramientas/cerrarSesion.png"));
        mnitCerrarSesion.setActionCommand("mnitCerrarSesion");
        mnArchivo.add(mnitCerrarSesion);

        JMenu mnEditar = new JMenu(idioma.getString("editar"));
        menuBar.add(mnEditar);

        mnitDeshacer = new JMenuItem(idioma.getString("deshacer"), new ImageIcon("media/herramientas/deshacer.png"));
        mnitDeshacer.setActionCommand("mnitDeshacer");
        mnEditar.add(mnitDeshacer);

        mnitPreferencias = new JMenuItem(idioma.getString("preferencias"), new ImageIcon("media/herramientas/preferencias.png"));
        mnitPreferencias.setActionCommand("mnitPreferencias");
        mnEditar.add(mnitPreferencias);

    }

}
