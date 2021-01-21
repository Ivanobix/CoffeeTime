package coffeetime.gui.principal;

import coffeetime.base.Cafe;
import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.componentes.Renderer;

import javax.swing.*;
import java.util.ResourceBundle;

public class Submenu extends JDialog {
    public static final int TYPE_CAFES = 0;
    public static final int TYPE_LOTES = 1;
    public static final int TYPE_FABRICANTES = 2;
    final int tipo;
    private final ResourceBundle idioma;
    JPanel contentPane;
    JPanel pnListado;
    JPanel pnEstadisticas;
    JTabbedPane tabbedPane;
    JButton btnAnadir;
    JButton btnModificar;
    JButton btnEliminar;
    JButton btnMostrarInfoAdicional;
    JButton btnCambiarGrafica;
    JTextField txtFiltro;
    JComboBox<String> cbFiltrado;
    JList listaElementos;
    DefaultListModel dlm;

    public Submenu(int tipo) {
        this.tipo = tipo;
        idioma = ResourceBundle.getBundle("idioma");
        setContentPane(contentPane);
        initComponents();
        internacionalizar();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        this.getRootPane().setDefaultButton(btnAnadir);
        btnAnadir.requestFocus();
    }

    private void initComponents() {
        rellenarFiltros();

        if (tipo == TYPE_FABRICANTES) {
            btnCambiarGrafica.setVisible(true);
        }
    }


    private void rellenarFiltros() {
        if (tipo == TYPE_CAFES) {
            rellenarFiltrosCafe();
        } else if (tipo == TYPE_LOTES) {
            rellenarFiltrosLote();
        } else {
            rellenarFiltrosFabricante();
        }
    }

    private void rellenarFiltrosCafe() {
        cbFiltrado.addItem(idioma.getString("general.nombre"));
        cbFiltrado.addItem(idioma.getString("general.arabico"));
        cbFiltrado.addItem(idioma.getString("general.robusta"));
        cbFiltrado.addItem(idioma.getString("general.lote"));
    }

    private void rellenarFiltrosLote() {
        cbFiltrado.addItem(idioma.getString("general.unidades"));
        cbFiltrado.addItem(idioma.getString("general.coste"));
        cbFiltrado.addItem(idioma.getString("general.fabricante"));
    }

    private void rellenarFiltrosFabricante() {
        cbFiltrado.addItem(idioma.getString("general.nombre"));
        cbFiltrado.addItem(idioma.getString("general.direccion"));
        cbFiltrado.addItem(idioma.getString("general.trabajadores"));
    }

    private void internacionalizar() {
        tabbedPane.setToolTipTextAt(0, idioma.getString("ver.Listado"));
        if (tipo != TYPE_CAFES) {
            tabbedPane.setToolTipTextAt(1, idioma.getString("ver.Estadisticas"));
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
