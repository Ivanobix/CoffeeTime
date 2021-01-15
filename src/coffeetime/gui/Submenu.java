package coffeetime.gui;

import javax.swing.*;

public class Submenu extends JDialog {
    public static final int TYPE_CAFES = 0;
    public static final int TYPE_LOTES = 1;
    public static final int TYPE_FABRICANTES = 2;

    private final int tipo;

    JPanel contentPane;
    JPanel pnListado;
    JPanel pnEstadisticas;
    JTabbedPane tabbedPane;
    JButton btnAnadir;
    JButton btnModificar;
    JButton btnEliminar;
    JButton btnMostrarInfoAdicional;
    JTextField textField1;
    JComboBox comboBox1;

    public Submenu(int tipo) {
        this.tipo = tipo;
        setContentPane(contentPane);
        initComponents();
        pack();
        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {

    }
}
