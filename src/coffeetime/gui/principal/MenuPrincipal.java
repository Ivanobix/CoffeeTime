package coffeetime.gui.principal;

import javax.swing.*;
import java.awt.*;


public class MenuPrincipal extends Component {
    private static final Color TEMA_OSCURO = new Color(21, 21, 21);

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
        frame = new JFrame("MenuPrincipal");
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

        JMenu mnArchivo = new JMenu("Archivo");
        menuBar.add(mnArchivo);

        mnitGuardar = new JMenuItem("Guardar", new ImageIcon("media/herramientas/guardar.png"));
        mnitGuardar.setActionCommand("mnitGuardar");
        mnArchivo.add(mnitGuardar);

        mnitCargar = new JMenuItem("Cargar", new ImageIcon("media/herramientas/cargar.png"));
        mnitCargar.setActionCommand("mnitCargar");
        mnArchivo.add(mnitCargar);

        mnitNuevo = new JMenuItem("Nuevo", new ImageIcon("media/herramientas/nuevo.png"));
        mnitNuevo.setActionCommand("mnitNuevo");
        mnArchivo.add(mnitNuevo);

        mnitCerrarSesion = new JMenuItem("Cerrar Sesión", new ImageIcon("media/herramientas/cerrarSesion.png"));
        mnitCerrarSesion.setActionCommand("mnitCerrarSesion");
        mnArchivo.add(mnitCerrarSesion);

        JMenu mnEditar = new JMenu("Editar");
        menuBar.add(mnEditar);

        mnitDeshacer = new JMenuItem("Deshacer", new ImageIcon("media/herramientas/deshacer.png"));
        mnitDeshacer.setActionCommand("mnitDeshacer");
        mnEditar.add(mnitDeshacer);

        mnitPreferencias = new JMenuItem("Preferencias", new ImageIcon("media/herramientas/preferencias.png"));
        mnitPreferencias.setActionCommand("mnitPreferencias");
        mnEditar.add(mnitPreferencias);

    }

}
