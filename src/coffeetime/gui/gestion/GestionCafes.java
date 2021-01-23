package coffeetime.gui.gestion;

import coffeetime.base.Lote;

import javax.swing.*;

/**
 * Gestión Café. Ventana dedicada a la recogida de datos para la
 * posterior creación y modificación de objetos tipo Café.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class GestionCafes extends JDialog {
    JPanel contentPane;
    JTextField txtNombre;
    JTextField txtArabico;
    JTextField txtRobusta;
    JTextField txtRutaImagen;
    JComboBox<Lote> cbLote;
    JButton btnGestionar;
    JButton btnCancelar;
    JButton btnSeleccionarImagen;
    JLabel imgPromocional;
    DefaultComboBoxModel<Lote> dcbm;

    /**
     * Constructor.
     */
    public GestionCafes() {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
     * los que dispone esta clase y establece sus propiedades.
     */
    private void initComponents() {
        contentPane.getRootPane().setDefaultButton(btnGestionar);
        btnGestionar.requestFocus();

        dcbm = new DefaultComboBoxModel<>();
        cbLote.setModel(dcbm);
    }

}
