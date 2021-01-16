package coffeetime.gui;

import coffeetime.base.Fabricante;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

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

    public GestionLotes() {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        dcbm = new DefaultComboBoxModel<>();
        cbFabricante.setModel(dcbm);
    }
}
