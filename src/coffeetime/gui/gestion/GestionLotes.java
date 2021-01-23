package coffeetime.gui.gestion;

import coffeetime.base.Fabricante;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

/**
 * Gestión Lotes. Ventana dedicada a la recogida de datos para la
 * posterior creación y modificación de objetos tipo Lote.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class GestionLotes extends JDialog {
    JPanel contentPane;
    JTextField txtUnidades;
    JTextField txtCoste;
    DatePicker dpCaducidad;
    JButton btnGestionar;
    JButton btnCancelar;
    JComboBox<Fabricante> cbFabricante;
    DatePicker dpEnvasado;
    DefaultComboBoxModel<Fabricante> dcbm;

    /**
     * Constructor.
     */
    public GestionLotes() {
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
        cbFabricante.setModel(dcbm);
    }
}
