package coffeetime.gui;

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

    public GestionFabricantes() {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
