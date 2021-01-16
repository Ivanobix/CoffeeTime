package coffeetime.gui;

import coffeetime.base.Cafe;
import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.componentes.Renderer;

import javax.swing.*;

public class Submenu extends JDialog {
    public static final int TYPE_CAFES = 0;
    public static final int TYPE_LOTES = 1;
    public static final int TYPE_FABRICANTES = 2;

    final int tipo;

    JPanel contentPane;
    JPanel pnListado;
    JPanel pnEstadisticas;
    JTabbedPane tabbedPane;
    JButton btnAnadir;
    JButton btnModificar;
    JButton btnEliminar;
    JButton btnMostrarInfoAdicional;
    JTextField txtFiltro;
    JComboBox<String> cbFiltrado;
    JList listaElementos;
    DefaultListModel dlm;

    public Submenu(int tipo) {
        this.tipo = tipo;
        setContentPane(contentPane);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        if (tipo == TYPE_CAFES) {
            tabbedPane.remove(1);
        }

    }

    private void createUIComponents() {
        if (tipo == TYPE_CAFES) {
            listaElementos = new JList<Cafe>();
            dlm = new DefaultListModel<Cafe>();
            listaElementos.setModel(dlm);
            listaElementos.setCellRenderer(new Renderer(Renderer.CAFES));
        } else if (tipo == TYPE_LOTES) {
            listaElementos = new JList<Lote>();
            dlm = new DefaultListModel<Lote>();
            listaElementos.setModel(dlm);
            listaElementos.setCellRenderer(new Renderer(Renderer.LOTES));
        } else {
            listaElementos = new JList<Fabricante>();
            dlm = new DefaultListModel<Fabricante>();
            listaElementos.setModel(dlm);
            listaElementos.setCellRenderer(new Renderer(Renderer.FABRICANTES));
        }

    }
}
