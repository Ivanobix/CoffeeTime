package coffeetime.gui;

import javax.swing.*;
import java.awt.*;


public class MenuPrincipal extends Component {
    private final JFrame frame;
    JPanel pnPrincipal;
    JPanel pnOpciones;
    JPanel pnDatos;
    JButton btnCafes;
    JButton btnConfiguracion;
    JButton btnFabricantes;
    JButton btnLotes;
    JMenuItem mnitNuevo;
    JMenuItem mnitGuardar;
    JMenuItem mnitCargar;
    JMenuItem mnitDeshacer;

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
    }

    private void initBarraDeHerramientas() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnArchivo = new JMenu("Archivo");
        menuBar.add(mnArchivo);

        mnitNuevo = new JMenuItem("Nuevo", new ImageIcon("media/iconos/nuevo.png"));
        mnitNuevo.setActionCommand("mnitNuevo");
        mnArchivo.add(mnitNuevo);

        mnitGuardar = new JMenuItem("Guardar", new ImageIcon("media/iconos/guardar.png"));
        mnitGuardar.setActionCommand("mnitGuardar");
        mnArchivo.add(mnitGuardar);

        mnitCargar = new JMenuItem("Cargar", new ImageIcon("media/iconos/cargar.png"));
        mnitCargar.setActionCommand("mnitCargar");
        mnArchivo.add(mnitCargar);

        JMenu mnEditar = new JMenu("Editar");
        menuBar.add(mnEditar);

        mnitDeshacer = new JMenuItem("Deshacer", new ImageIcon(""));
        mnitDeshacer.setActionCommand("mnitDeshacer");
        mnEditar.add(mnitDeshacer);

    }

}
