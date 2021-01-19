package coffeetime.gui.gestion;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

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

    public GestionFabricantes() {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        contentPane.getRootPane().setDefaultButton(btnGestionar);
        btnGestionar.requestFocus();

    }

}
