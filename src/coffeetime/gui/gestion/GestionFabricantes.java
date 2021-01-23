package coffeetime.gui.gestion;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

/**
 * Gestión Fabricante. Ventana dedicada a la recogida de datos para la
 * posterior creación y modificación de objetos tipo Fabricante.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class GestionFabricantes extends JDialog {
    JPanel contentPane;
    JTextField txtNombre;
    JTextField txtDireccion;
    JTextField txtTrabajadores;
    DatePicker dpFechaAlta;
    JCheckBox chbxInternacional;
    JButton btnGestionar;
    JButton btnCancelar;
    JButton btnGestionarLotes;
    JLabel lblLotesFabricados;

    /**
     * Constructor.
     */
    public GestionFabricantes() {
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
        setIconImage(new ImageIcon(this.getClass().getResource("/general/logo.png")).getImage());
        contentPane.getRootPane().setDefaultButton(btnGestionar);
        btnGestionar.requestFocus();
    }

}
